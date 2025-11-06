package com.example.evsalesmanagement.dto.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class FeadbackRequestDTO {

    @NotNull(message = "Customer ID is required")
    @Positive(message = "Customer ID must be positive")
    private Integer customerId;

    @NotBlank(message = "Feedback title cannot be blank")
    @Size(min = 5, max = 200, message = "Title must be between 5 and 200 characters")
    private String feedbackTitle;

    @NotBlank(message = "Feedback content cannot be blank")
    @Size(min = 10, max = 2000, message = "Content must be between 10 and 2000 characters")
    private String feedbackContent;

    // Getters and Setters
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
}