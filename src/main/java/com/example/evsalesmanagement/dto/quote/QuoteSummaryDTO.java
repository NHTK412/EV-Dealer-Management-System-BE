package com.example.evsalesmanagement.dto.quote;

import java.math.BigDecimal;

import com.example.evsalesmanagement.enums.QuoteStatusEnum;
import com.example.evsalesmanagement.model.Quote;

public class QuoteSummaryDTO {
    private Integer quoteId;

    private Integer employeeId;

    private String employeeName;

    private String employeePhoneNumber;

    private String employeeEmail;

    private BigDecimal totalAmount;

    private QuoteStatusEnum status;

    public QuoteSummaryDTO(Quote quote) {
        this.quoteId = quote.getQuoteId();
        this.employeeId = quote.getEmployee().getEmployeeId();
        this.employeeName = quote.getEmployee().getEmployeeName();
        this.employeePhoneNumber = quote.getEmployee().getPhoneNumber();
        this.employeeEmail = quote.getEmployee().getEmail();
        this.totalAmount = quote.getTotalAmount();
        this.status = quote.getStatus();
    }

    public QuoteSummaryDTO() {
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public QuoteStatusEnum getStatus() {
        return status;
    }

    public void setStatus(QuoteStatusEnum status) {
        this.status = status;
    }

}
