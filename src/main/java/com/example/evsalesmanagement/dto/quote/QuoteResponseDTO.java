package com.example.evsalesmanagement.dto.quote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.dto.quotationdetail.QuotationDetailResponseDTO;
import com.example.evsalesmanagement.enums.QuoteStatusEnum;
import com.example.evsalesmanagement.model.Quote;

public class QuoteResponseDTO {

    private Integer quoteId;

    private Integer customerId;

    private String customerName;

    private String customerPhone;

    private String customerEmail;

    private Integer employeeId;

    private String employeeName;

    private String employeePhoneNumber;

    private String employeeEmail;

    private BigDecimal totalAmount;

    private QuoteStatusEnum status;

    private List<QuotationDetailResponseDTO> quotationDetailResponseDTOs = new ArrayList<>();

    public QuoteResponseDTO(Quote quote) {
        this.quoteId = quote.getQuoteId();
        this.employeeId = quote.getEmployee().getEmployeeId();
        this.employeeName = quote.getEmployee().getEmployeeName();
        this.employeePhoneNumber = quote.getEmployee().getPhoneNumber();
        this.employeeEmail = quote.getEmployee().getEmail();
        this.totalAmount = quote.getTotalAmount();
        this.status = quote.getStatus();

        this.customerId = quote.getCustomer().getCustomerId();
        this.customerName = quote.getCustomer().getCustomerName();
        this.customerPhone = quote.getCustomer().getPhoneNumber();
        this.customerEmail = quote.getCustomer().getEmail();

        this.quotationDetailResponseDTOs = quote.getQuotationDetails().stream().map(quotationDetail -> {
            return new QuotationDetailResponseDTO(quotationDetail);
        }).toList();
    }

    public QuoteResponseDTO() {
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

    public List<QuotationDetailResponseDTO> getQuotationDetails() {
        return quotationDetailResponseDTOs;
    }

    public void setQuotationDetails(List<QuotationDetailResponseDTO> quotationDetails) {
        this.quotationDetailResponseDTOs = quotationDetails;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    // quotationDetailResponseDTOs) {
    // this.quotationDetailResponseDTOs = quotationDetailResponseDTOs;

}
