package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//ThanhToan = Payment
@Entity
@Table(name = "Payment")
public class Payment extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentId")
    private Integer paymentId;

    // PhuongThucThanhToan = PaymentMethod
    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @Column(name = "PaymentForm")
    private String paymentForm;

    // SoTien = Amount
    @Column(name = "Amount")
    private BigDecimal amount;

    // chukyso = CycleNumber
    @Column(name = "NumberCycle")
    private Integer numberCycle;

    // NgayDenHan = DueDate
    @Column(name = "DueDate")
    private LocalDateTime dueDate;

    // NgayThanhToan = PaymentDate
    @Column(name = "PaymentDate")
    private LocalDateTime paymentDate;

    // TienPhat = PenaltyAmount

    @Column(name = "PenaltyAmount")
    private BigDecimal penaltyAmount;

    @Column(name = "Status")
    private String status;

    @Column(name = "VNPAYCode")
    private String vnpayCode;

    @ManyToOne
    @JoinColumn(name = "OrderId")
    private Order order;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVnpayCode() {
        return vnpayCode;
    }

    public void setVnpayCode(String vnpayCode) {
        this.vnpayCode = vnpayCode;
    }

    public Order getOder() {
        return order;
    }

    public void setOder(Order oder) {
        this.order = oder;
    }

}
