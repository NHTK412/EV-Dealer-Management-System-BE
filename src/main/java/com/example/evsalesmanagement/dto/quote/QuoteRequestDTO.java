package com.example.evsalesmanagement.dto.quote;

import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.dto.quotationdetail.QuotationDetailRequestDTO;

public class QuoteRequestDTO {

    private Integer employeeId;

    private String status;

    private List<QuotationDetailRequestDTO> quotationDetailRequestDTOs = new ArrayList<>();

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<QuotationDetailRequestDTO> getQuotationDetailRequestDTOs() {
        return quotationDetailRequestDTOs;
    }

    public void setQuotationDetailRequestDTOs(List<QuotationDetailRequestDTO> quotationDetailRequestDTOs) {
        this.quotationDetailRequestDTOs = quotationDetailRequestDTOs;
    }

}
