package com.example.evsalesmanagement.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FeedbackDetailDTO {
    private Integer feedbackId;
    private String feedbackTitle;
    private String feedbackContent;
    private String status;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
    
    private Integer customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    
    private Integer feedbackHandlingId;
    private String feedbackHandlingContent;
    private String feedbackHandlingMethod;
    private String handlingStatus;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handlingCreateAt;
    
    private Integer employeeId;
    private String employeeName;
    private String employeeEmail;

    public FeedbackDetailDTO() {}

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getFeedbackHandlingId() {
        return feedbackHandlingId;
    }

    public void setFeedbackHandlingId(Integer feedbackHandlingId) {
        this.feedbackHandlingId = feedbackHandlingId;
    }

    public String getFeedbackHandlingContent() {
        return feedbackHandlingContent;
    }

    public void setFeedbackHandlingContent(String feedbackHandlingContent) {
        this.feedbackHandlingContent = feedbackHandlingContent;
    }

    public String getFeedbackHandlingMethod() {
        return feedbackHandlingMethod;
    }

    public void setFeedbackHandlingMethod(String feedbackHandlingMethod) {
        this.feedbackHandlingMethod = feedbackHandlingMethod;
    }

    public String getHandlingStatus() {
        return handlingStatus;
    }

    public void setHandlingStatus(String handlingStatus) {
        this.handlingStatus = handlingStatus;
    }

    public LocalDateTime getHandlingCreateAt() {
        return handlingCreateAt;
    }

    public void setHandlingCreateAt(LocalDateTime handlingCreateAt) {
        this.handlingCreateAt = handlingCreateAt;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}