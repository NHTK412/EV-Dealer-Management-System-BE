package com.example.evsalesmanagement.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PaymentPlan")
public class PaymentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentPlanId;

    @Column(name = "PaymentName")
    private String paymentName;

    @Column(name = "NumberOfInstallments")
    private Integer numberOfInstallments;

    @Column(name = "DownPaymentPercent")
    private BigDecimal downPaymentPercent;

    @Column(name = "InterestRate")
    private BigDecimal interestRate;

    public Integer getPaymentPlanId() {
        return paymentPlanId;
    }

    public void setPaymentPlanId(Integer paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public BigDecimal getDownPaymentPercent() {
        return downPaymentPercent;
    }

    public void setDownPaymentPercent(BigDecimal downPaymentPercent) {
        this.downPaymentPercent = downPaymentPercent;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

}
