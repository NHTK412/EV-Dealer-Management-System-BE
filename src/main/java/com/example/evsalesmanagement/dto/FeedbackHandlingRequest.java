package com.example.evsalesmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// ==================== REQUEST DTOs ====================

/**
 * DTO để xử lý phản hồi
 */
public class FeedbackHandlingRequest {

    // @NotNull(message = "Mã phản hồi không được để trống")
    // @Positive(message = "Mã phản hồi phải là số dương")
    // private Integer maPhanHoi;

    @NotBlank(message = "Nội dung xử lý không được để trống")
    @Size(min = 10, max = 2000, message = "Nội dung xử lý phải từ 10-2000 ký tự")
    private String feedbackHandling;

    @NotBlank(message = "Hình thức giải quyết không được để trống")
    private String feedbackHandlingMethod; // "Điện thoại" hoặc "Email"

    
    public FeedbackHandlingRequest() {}


    public String getFeedbackHandling() {
        return feedbackHandling;
    }


    public void setFeedbackHandling(String feedbackHandling) {
        this.feedbackHandling = feedbackHandling;
    }


    public String getFeedbackHandlingMethod() {
        return feedbackHandlingMethod;
    }


    public void setFeedbackHandlingMethod(String feedbackHandlingMethod) {
        this.feedbackHandlingMethod = feedbackHandlingMethod;
    }

    // public Integer getMaPhanHoi() {
    //     return maPhanHoi;
    // }

    // public void setMaPhanHoi(Integer maPhanHoi) {
    //     this.maPhanHoi = maPhanHoi;
    // }

}