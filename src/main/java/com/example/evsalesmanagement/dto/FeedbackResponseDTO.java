package com.example.evsalesmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO response danh sách phản hồi (1 bảng to chứa toàn bộ các phản hồi con)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackResponseDTO {

    private Integer feedbackId;
    private String feedbackTitle;
    private String feedbackContent;
    private String status;
    
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // private LocalDateTime ngayTao;
    
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // private LocalDateTime ngayCapNhat;
    
    // Thông tin khách hàng
    // private Integer maKhachHang;
    private String customerName;
    // private String emailKhachHang;
    // private String soDienThoaiKhachHang;

    public FeedbackResponseDTO() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FeedbackResponseDTO response = new FeedbackResponseDTO();

        public Builder FeedbackId(Integer feedbackId) {
            response.feedbackId = feedbackId;
            return this;
        }

        public Builder FeedbackTitle(String feedbackTitle) {
            response.feedbackTitle = feedbackTitle;
            return this;
        }

        public Builder FeedbackContent(String feedbackContent) {
            response.feedbackContent = feedbackContent;
            return this;
        }

        public Builder Status(String status) {
            response.status = status;
            return this;
        }

        // public Builder ngayTao(LocalDateTime ngayTao) {
        //     response.ngayTao = ngayTao;
        //     return this;
        // }

        // public Builder ngayCapNhat(LocalDateTime ngayCapNhat) {
        //     response.ngayCapNhat = ngayCapNhat;
        //     return this;
        // }

        // public Builder maKhachHang(Integer maKhachHang) {
        //     response.maKhachHang = maKhachHang;
        //     return this;
        // }

        public Builder tenKhachHang(String customerName) {
            response.customerName = customerName;
            return this;
        }

        // public Builder emailKhachHang(String emailKhachHang) {
        //     response.emailKhachHang = emailKhachHang;
        //     return this;
        // }

        // public Builder soDienThoaiKhachHang(String soDienThoaiKhachHang) {
        //     response.soDienThoaiKhachHang = soDienThoaiKhachHang;
        //     return this;
        // }

        public FeedbackResponseDTO build() {
            return response;
        }
    }

    // Getters
    public Integer getFeedbackId() { return feedbackId; }
    public String getFeedbackTitle() { return feedbackTitle; }
    public String getfeedbackContent() { return feedbackContent; }
    public String getStatus() { return status; }
    // public LocalDateTime getNgayTao() { return ngayTao; }
    // public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }
    // public Integer getMaKhachHang() { return maKhachHang; }
    public String getCustomerName() { return customerName; }
    // public String getEmailKhachHang() { return emailKhachHang; }
    // public String getSoDienThoaiKhachHang() { return soDienThoaiKhachHang; }
}