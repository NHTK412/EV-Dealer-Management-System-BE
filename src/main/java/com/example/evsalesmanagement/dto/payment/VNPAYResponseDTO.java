package com.example.evsalesmanagement.dto.payment;

public class VNPAYResponseDTO {
    private String paymentUrl;
    private String vnpayCode;
    private String message;

    public VNPAYResponseDTO(String paymentUrl, String vnpayCode, String message) {
        this.paymentUrl = paymentUrl;
        this.vnpayCode = vnpayCode;
        this.message = message;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getVnpayCode() {
        return vnpayCode;
    }

    public void setVnpayCode(String vnpayCode) {
        this.vnpayCode = vnpayCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}