package com.example.evsalesmanagement.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.CreateFeedbackRequestDTO;
import com.example.evsalesmanagement.dto.FeedbackDetailDTO;
import com.example.evsalesmanagement.dto.FeedbackListDTO;
import com.example.evsalesmanagement.enums.FeedbackStatusEnum;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Feedback;
import com.example.evsalesmanagement.model.FeedbackHandling;
import com.example.evsalesmanagement.repository.CustomerRepository;
import com.example.evsalesmanagement.repository.FeedbackRepository;

@Service
@Transactional
public class FeedbackService {
    
    private static final Logger log = LoggerFactory.getLogger(FeedbackService.class);
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    // tạo feedback mới
    @Transactional
    public FeedbackDetailDTO createFeedback(CreateFeedbackRequestDTO request) {
        log.info("Creating feedback from customer: {}", request.getCustomerId());

        // log bug
    if (request.getFeedbackTitle() == null || request.getFeedbackTitle().isBlank()) {
        throw new BadRequestException("Feedback title is required.");
    }

    if (request.getFeedbackContent() == null || request.getFeedbackContent().isBlank()) {
        throw new BadRequestException("Feedback content is required.");
    }

        Customer customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Customer not found with ID: " + request.getCustomerId()));
        
        Feedback feedback = new Feedback();
        feedback.setCustomer(customer);
        feedback.setFeedbackTitle(request.getFeedbackTitle());
        feedback.setFeedbackContent(request.getFeedbackContent());
        feedback.setFeedbackStatus(FeedbackStatusEnum.NOT_YET_PROCESSED);
        feedback.setCreateAt(LocalDateTime.now());
        feedback.setUpdateAt(LocalDateTime.now());
        
        Feedback savedFeedback = feedbackRepository.save(feedback);
        
        // log.info("Feedback created successfully with ID: {}", savedFeedback.getFeedbackId());
        
        return convertToDetailDTO(savedFeedback);
    }

    // lấy danh sách feedback với phân trang + lọc 
    @Transactional(readOnly = true)
    public Page<FeedbackListDTO> getFeedback(String statusStr, Pageable pageable) {
        // log.debug("Getting feedback - status: {}, page: {}", statusStr, pageable.getPageNumber());
        
        if (statusStr == null || statusStr.trim().isEmpty()) {
            return getAllFeedback(pageable);
        }
        
        return getFeedbackByStatus(statusStr, pageable);
    }
    
   // lấy tất cả feedback + phân trang
    public Page<FeedbackListDTO> getAllFeedback(Pageable pageable) {
        // log.debug("Getting all feedback - page: {}, size: {}", 
        //     pageable.getPageNumber(), pageable.getPageSize());
        
        return feedbackRepository.findAllByOrderByCreateAtDesc(pageable)
            .map(this::convertToListDTO);
    }
    
    // lấy feedback theo trạng thái + phân trang
    public Page<FeedbackListDTO> getFeedbackByStatus(String statusStr, Pageable pageable) {
        // log.debug("Getting feedback by status: {}", statusStr);
        
        FeedbackStatusEnum status = validateAndParseStatus(statusStr);
        
        return feedbackRepository.findByFeedbackStatusOrderByCreateAtDesc(status, pageable)
            .map(this::convertToListDTO);
    }
    
    // lấy chi tiết feedback theo id
    public FeedbackDetailDTO getFeedbackDetail(Integer feedbackId) {
        // log.debug("Getting feedback detail: {}", feedbackId);
        
        Feedback feedback = findFeedbackById(feedbackId);
        
        return convertToDetailDTO(feedback);
    }
    
    // đếm số lượng feedback theo trạng thái
    public Long countByStatus(String statusStr) {
        // log.debug("Counting feedback by status: {}", statusStr);
        
        FeedbackStatusEnum status = validateAndParseStatus(statusStr);
        
        return feedbackRepository.countByFeedbackStatus(status);
    }
    
    // tìm feedback theo id
    public Feedback findFeedbackById(Integer feedbackId) {
        return feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Feedback not found with ID: " + feedbackId));
    }
    
    // validate + chuyển status String sang enum
    private FeedbackStatusEnum validateAndParseStatus(String statusStr) {
        if (!FeedbackStatusEnum.enumIsValid(statusStr)) {
            throw new BadRequestException("Invalid status: " + statusStr);
        }
        
        return FeedbackStatusEnum.fromStringToEnum(statusStr);
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