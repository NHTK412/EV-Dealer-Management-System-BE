package com.example.evsalesmanagement.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.PaymentMethodEnum;
import com.example.evsalesmanagement.enums.PaymentStatusEnum;

public class PaymentResponseDTO {

    private Integer paymentId;
    private PaymentMethodEnum paymentMethod;
    private String paymentForm;
    private BigDecimal amount;
    private Integer numberCycle;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;
    private BigDecimal penaltyAmount;
    private PaymentStatusEnum status;
    private String vnpayCode;
    private Integer orderId;

    // Getters & Setters
    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getNumberCycle() {
        return numberCycle;
    }

    public void setNumberCycle(Integer numberCycle) {
        this.numberCycle = numberCycle;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public PaymentStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PaymentStatusEnum status) {
        this.status = status;
    }

    public String getVnpayCode() {
        return vnpayCode;
    }

    public void setVnpayCode(String vnpayCode) {
        this.vnpayCode = vnpayCode;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
