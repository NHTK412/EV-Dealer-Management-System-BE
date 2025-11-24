package com.example.evsalesmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.feedback.FeedbackResponseDTO;
import com.example.evsalesmanagement.dto.handlefeedback.HandleFeedbackRequestDTO;
import com.example.evsalesmanagement.enums.FeedbackHandlingMethodEnum;
import com.example.evsalesmanagement.enums.FeedbackHandlingStatusEnum;
import com.example.evsalesmanagement.enums.FeedbackStatusEnum;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Feedback;
import com.example.evsalesmanagement.model.FeedbackHandling;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.FeedbackHandlingRepository;
import com.example.evsalesmanagement.repository.FeedbackRepository;

@Service
public class FeedbackHandlingService {
    
    private static final Logger log = LoggerFactory.getLogger(FeedbackHandlingService.class);
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Autowired
    private FeedbackHandlingRepository feedbackHandlingRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private FeedbackService feedbackService;
    
    // xử lý phản hồi
    @Transactional
    public FeedbackResponseDTO handleFeedback(
            Integer feedbackId, 
            HandleFeedbackRequestDTO request, 
            Integer employeeId) {
        
        log.info("Processing feedback {} by employee {}", feedbackId, employeeId);
    
        Feedback feedback = validateFeedbackForHandling(feedbackId);
        Employee employee = findEmployeeById(employeeId);
        FeedbackHandlingMethodEnum method = validateHandlingMethod(request.getFeedbackHandlingMethod());
        
        updateFeedbackStatus(feedback, FeedbackStatusEnum.IN_PROCESSED);
        log.debug("Transition feedback {} to IN_PROCESSED", feedbackId);
        
        FeedbackHandling handling = createHandlingRecord(feedback, employee, request, method);
        log.debug("Created FeedbackHandling with ID {}", handling.getFeedbackHandlingId());
        
        feedback.setFeedbackHandling(handling);
        feedbackRepository.save(feedback);
        
        updateFeedbackStatus(feedback, FeedbackStatusEnum.PROCESSED);
        log.debug("Transition feedback {} to PROCESSED", feedbackId);
        
        notifyCustomer(feedback, handling);
        log.info("Successfully processed feedback {}", feedbackId);
        
        return feedbackService.getFeedbackDetail(feedbackId);
    }
    
    // validate phản hồi có thể xử lý
    private Feedback validateFeedbackForHandling(Integer feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Feedback not found with ID: " + feedbackId));
        
        if (feedbackHandlingRepository.existsByFeedback_FeedbackId(feedbackId)) {
            throw new ConflictException("Feedback has already been handled");
        }
        
        if (feedback.getFeedbackStatus() == FeedbackStatusEnum.PROCESSED) {
            throw new BadRequestException("Feedback is already in processed status");
        }
        
        return feedback;
    }
    
    // tìm nhân viên theo ID
    private Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Employee not found with ID: " + employeeId));
    }
    
    // validate phương thức phản hồi
    private FeedbackHandlingMethodEnum validateHandlingMethod(String methodStr) {
        if (!FeedbackHandlingMethodEnum.enumIsValid(methodStr)) {
            throw new BadRequestException("Invalid handling method: " + methodStr);
        }
        
        return FeedbackHandlingMethodEnum.fromStringToEnum(methodStr);
    }
    
    // cập nhật trạng thái phản hồi
    private void updateFeedbackStatus(Feedback feedback, FeedbackStatusEnum newStatus) {
        FeedbackStatusEnum currentStatus = feedback.getFeedbackStatus();
        
        if (!currentStatus.canTransitionToNewStatus(newStatus)) {
            throw new BadRequestException(
                String.format("Cannot transition from %s to %s", 
                    currentStatus.getDisplayName(), 
                    newStatus.getDisplayName()));
        }
        
        feedback.setFeedbackStatus(newStatus);
        feedbackRepository.save(feedback);
    }
    
    // tạo bản ghi xử lý phản hồi
    private FeedbackHandling createHandlingRecord(
            Feedback feedback, 
            Employee employee, 
            HandleFeedbackRequestDTO request,
            FeedbackHandlingMethodEnum method) {
        
        log.debug("Creating FeedbackHandling for feedback {}", feedback.getFeedbackId());
        
        FeedbackHandling handling = new FeedbackHandling();
        handling.setFeedback(feedback);
        handling.setEmployee(employee);
        handling.setFeedbackHandlingContent(request.getFeedbackHandlingContent());
        handling.setFeedbackHandlingMethod(method);
        handling.setStatus(FeedbackHandlingStatusEnum.COMPLETE);
        
        FeedbackHandling saved = feedbackHandlingRepository.save(handling);
        
        log.debug("Saved FeedbackHandling: {}", saved.getFeedbackHandlingId());
        
        return saved;
    }
    
    // gửi thông báo cho khách hàng 
    private void notifyCustomer(Feedback feedback, FeedbackHandling handling) {
        try {
            FeedbackHandlingMethodEnum method = handling.getFeedbackHandlingMethod();
            Customer customer = feedback.getCustomer();
            
            if (customer == null) {
                log.warn("Cannot send notification - customer is null for feedback {}", 
                    feedback.getFeedbackId());
                return;
            }
            
            if (method == FeedbackHandlingMethodEnum.EMAIL) {
                sendEmailNotification(customer, feedback, handling);
            } else if (method == FeedbackHandlingMethodEnum.PHONE_NUMBER) {
                log.info("Phone notification scheduled for customer: {} (Not implemented yet)", 
                    customer.getPhoneNumber());
            }
            
        } catch (Exception e) {
            log.error("Failed to send notification for feedback {}: {}", 
                feedback.getFeedbackId(), e.getMessage(), e);
        }
    }
    
    // gửi email thông báo
    private void sendEmailNotification(
            Customer customer, 
            Feedback feedback, 
            FeedbackHandling handling) {
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            log.warn("Customer {} has no email address", customer.getCustomerId());
            return;
        }
        
        try {
            emailService.sendFeedbackResponseEmail(
                customer.getEmail(),
                customer.getCustomerName(),
                feedback.getFeedbackTitle(),
                feedback.getFeedbackContent(),
                handling.getFeedbackHandlingContent()
            );
            
            log.info("Email notification sent to: {}", customer.getEmail());
            
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", 
                customer.getEmail(), e.getMessage(), e);
            throw e; 
        }
    }
}