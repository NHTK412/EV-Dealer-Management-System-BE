package com.example.evsalesmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO response sau khi xử lý phản hồi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackHandlingResponse {
    
    private FeedbackHandlingRequest feedbackHandling;
    
    /**
     * Mailto link để mở ứng dụng email trên thiết bị
     * Chỉ có khi hinhThucGiaiQuyet = "Email"
     */
    private String mailtoLink;
    
    public FeedbackHandlingResponse() {}

    public FeedbackHandlingRequest getFeedbackHandling() {
        return feedbackHandling;
    }

    public void setFeedbackHandling(FeedbackHandlingRequest feedbackHandling) {
        this.feedbackHandling = feedbackHandling;
    }

    public String getMailtoLink() {
        return mailtoLink;
    }

    public void setMailtoLink(String mailtoLink) {
        this.mailtoLink = mailtoLink;
    }
    
  
}