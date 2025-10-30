package com.example.evsalesmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.FeedbackDetailDTO;
import com.example.evsalesmanagement.dto.HandleFeedbackRequestDTO;
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
    
    private final FeedbackRepository feedbackRepository;
    private final FeedbackHandlingRepository feedbackHandlingRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;
    private final FeedbackService feedbackService;
    
    
    public FeedbackHandlingService(
            FeedbackRepository feedbackRepository,
            FeedbackHandlingRepository feedbackHandlingRepository,
            EmployeeRepository employeeRepository,
            EmailService emailService,
            FeedbackService feedbackService) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackHandlingRepository = feedbackHandlingRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
        this.feedbackService = feedbackService;
    }
    
    @Transactional
    public FeedbackDetailDTO handleFeedback(Integer feedbackId, HandleFeedbackRequestDTO request, Integer employeeId) {
        // log.info("Handling feedback {} by employee {}", feedbackId, employeeId);
        
        Feedback feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new ResourceNotFoundException("Not found feedback with ID: " + feedbackId));
        
        if (feedbackHandlingRepository.existsByFeedback_FeedbackId(feedbackId)) {
            throw new ConflictException("Feedback has already been handled");
        }
        
        if (feedback.getFeedbackStatus() == FeedbackStatusEnum.PROCESSED) {
            throw new BadRequestException("Feedback is already in processed status");
        }
        
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Not found employee with ID: " + employeeId));
        
        if (!FeedbackHandlingMethodEnum.enumIsValid(request.getFeedbackHandlingMethod())) {
            throw new BadRequestException("Handling method is invalid: " + request.getFeedbackHandlingMethod());
        }
        FeedbackHandlingMethodEnum method = FeedbackHandlingMethodEnum.fromStringToEnum(request.getFeedbackHandlingMethod());
        
        feedback.setFeedbackStatus(FeedbackStatusEnum.IN_PROCESSED);
        feedbackRepository.save(feedback);
        log.info("Updated feedback {} status to IN_PROCESSED", feedbackId);
        
        FeedbackHandling handling = new FeedbackHandling();
        handling.setFeedback(feedback);
        handling.setEmployee(employee);
        handling.setFeedbackHandlingContent(request.getFeedbackHandlingContent());
        handling.setFeedbackHandlingMethod(method);
        handling.setStatus(FeedbackHandlingStatusEnum.COMPLETE);
        
        feedbackHandlingRepository.save(handling);
        // log.info("Created feedback handling record");
        
        feedback.setFeedbackStatus(FeedbackStatusEnum.PROCESSED);
        feedbackRepository.save(feedback);
        // log.info("Updated feedback {} status to PROCESSED", feedbackId);
        

        // bug với database đang khó, tạm thời run tốt - hiện log
        if (method == FeedbackHandlingMethodEnum.EMAIL) {
            Customer customer = feedback.getCustomer();
            if (customer != null && customer.getEmail() != null) {
                try {
                    emailService.sendFeedbackResponseEmail(
                        customer.getEmail(),
                        customer.getCustomerName(),
                        feedback.getFeedbackTitle(),
                        feedback.getFeedbackContent(),
                        request.getFeedbackHandlingContent()
                    );
                    log.info("Email sent to customer: {}", customer.getEmail());
                } catch (Exception e) {
                    log.error("Failed to send email but handling completed: {}", e.getMessage());
                }
            }
        }
        
        return feedbackService.getFeedbackDetail(feedbackId);
    }
}