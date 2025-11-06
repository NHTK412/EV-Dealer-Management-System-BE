package com.example.evsalesmanagement.dto.feedback;

public class FeedBackSummaryDTO {
    private Integer feedbackId;
    private String feedbackTitle;
    private String feedbackContent;
    private String status;
    private Integer customerId;
    private String customerName;

    public FeedBackSummaryDTO() {
    }

    public FeedBackSummaryDTO(Integer feedbackId, String feedbackTitle, String feedbackContent,
            String status, Integer customerId, String customerName) {
        this.feedbackId = feedbackId;
        this.feedbackTitle = feedbackTitle;
        this.feedbackContent = feedbackContent;
        this.status = status;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    // Getters and Setters
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
}