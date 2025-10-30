package com.example.evsalesmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.FeedbackDetailDTO;
import com.example.evsalesmanagement.dto.FeedbackListDTO;
import com.example.evsalesmanagement.enums.FeedbackStatusEnum;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Feedback;
import com.example.evsalesmanagement.model.FeedbackHandling;
import com.example.evsalesmanagement.repository.FeedbackRepository;

@Service
public class FeedbackService {
    
    // private static final Logger log = LoggerFactory.getLogger(FeedbackService.class);
    
    private final FeedbackRepository feedbackRepository;
    
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
    
    
    @Transactional(readOnly = true)
    public Page<FeedbackListDTO> getAllFeedback(Pageable pageable) {
        // log.info("Getting all feedback - Page: {}, Size: {}", 
        //     pageable.getPageNumber(), pageable.getPageSize());
        
        Page<Feedback> feedbackPage = feedbackRepository.findAllByOrderByCreateAtDesc(pageable);
        
        return feedbackPage.map(this::convertToListDTO);
    }
    

    @Transactional(readOnly = true)
    public Page<FeedbackListDTO> getFeedbackByStatus(String statusStr, Pageable pageable) {
        // log.info("Getting feedback by status: {}", statusStr);
        
        if (!FeedbackStatusEnum.enumIsValid(statusStr)) {
            throw new BadRequestException("Status is invalid: " + statusStr);
        }
        
        FeedbackStatusEnum status = FeedbackStatusEnum.fromStringToEnum(statusStr);
        Page<Feedback> feedbackPage = feedbackRepository.findByFeedbackStatusOrderByCreateAtDesc(status, pageable);
        
        return feedbackPage.map(this::convertToListDTO);
    }
    

    @Transactional(readOnly = true)
    public FeedbackDetailDTO getFeedbackDetail(Integer feedbackId) {
        // log.info("Getting feedback detail: {}", feedbackId);
        
        Feedback feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with ID: " + feedbackId));
        
        return convertToDetailDTO(feedback);
    }
    

    @Transactional(readOnly = true)
    public Long countByStatus(String statusStr) {
        // log.info("Counting feedback by status: {}", statusStr);
        
        if (!FeedbackStatusEnum.enumIsValid(statusStr)) {
            throw new BadRequestException("Status is invalid: " + statusStr);
        }
        
        FeedbackStatusEnum status = FeedbackStatusEnum.fromStringToEnum(statusStr);
        return feedbackRepository.countByFeedbackStatus(status);
    }
    

    private FeedbackListDTO convertToListDTO(Feedback feedback) {
        Customer customer = feedback.getCustomer();
        
        return new FeedbackListDTO(
            feedback.getFeedbackId(),
            feedback.getFeedbackTitle(),
            feedback.getFeedbackContent(),
            feedback.getFeedbackStatus().getDisplayName(),
            customer != null ? customer.getCustomerId() : null,
            customer != null ? customer.getCustomerName() : null
        );
    }
    
    private FeedbackDetailDTO convertToDetailDTO(Feedback feedback) {
        FeedbackDetailDTO dto = new FeedbackDetailDTO();
        
        dto.setFeedbackId(feedback.getFeedbackId());
        dto.setFeedbackTitle(feedback.getFeedbackTitle());
        dto.setFeedbackContent(feedback.getFeedbackContent());
        dto.setStatus(feedback.getFeedbackStatus().getDisplayName());
        dto.setCreateAt(feedback.getCreateAt());
        dto.setUpdateAt(feedback.getUpdateAt());
        
        Customer customer = feedback.getCustomer();
        if (customer != null) {
            dto.setCustomerId(customer.getCustomerId());
            dto.setCustomerName(customer.getCustomerName());
            dto.setCustomerEmail(customer.getEmail());
            dto.setCustomerPhone(customer.getPhoneNumber());
        }
         
        FeedbackHandling handling = feedback.getFeedbackHandling();
        if (handling != null) {
            dto.setFeedbackHandlingId(handling.getFeedbackHandlingId());
            dto.setFeedbackHandlingContent(handling.getFeedbackHandlingContent());
            dto.setFeedbackHandlingMethod(handling.getFeedbackHandlingMethod().getDisplayName());
            dto.setHandlingStatus(handling.getStatus().getDisplayName());
            dto.setHandlingCreateAt(handling.getCreateAt());
            
            Employee employee = handling.getEmployee();
            if (employee != null) {
                dto.setEmployeeId(employee.getEmployeeId());
                dto.setEmployeeName(employee.getEmployeeName());
                dto.setEmployeeEmail(employee.getEmail());
            }
        }
        
        return dto;
    }
}