package com.example.evsalesmanagement.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.PaymentMethodEnum;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class PaymentCreateSummaryDTO {
    
    @NotNull(message = "Order ID not be null")
    private Integer orderId;

    @NotNull(message = "Amount not be null")
    @DecimalMin( value = "0.0", inclusive = false, message = "Amount phải lớn hơn 0")
    private BigDecimal amount;

    @NotNull(message = "Due Date not be null")
    private LocalDateTime dueDate;

    @NotNull(message = "Payment Method not be null")
    private PaymentMethodEnum paymentMethod;

    @NotNull(message = "Payment Form not be null")
    private String paymentForm;

    private Integer numberCycle; 

    // Getter + Setter 
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
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

    public Integer getNumberCycle() {
        return numberCycle;
    }

    public void setNumberCycle(Integer numberCycle) {
        this.numberCycle = numberCycle;
    }
}
