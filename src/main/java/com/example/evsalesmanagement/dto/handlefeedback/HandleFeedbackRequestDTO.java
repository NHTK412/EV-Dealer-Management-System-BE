package com.example.evsalesmanagement.dto.handlefeedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class HandleFeedbackRequestDTO {

    @NotBlank(message = "Content processing feedback cannot be blank")
    @Size(min = 10, max = 2000, message = "Content processing feedback must be between 10 and 2000 characters")
    private String feedbackHandlingContent;

    @NotBlank(message = "Feedback handling method cannot be blank")
    private String feedbackHandlingMethod; 

    public HandleFeedbackRequestDTO() {}

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

    
}