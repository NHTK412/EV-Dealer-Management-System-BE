package com.example.evsalesmanagement.dto.quote;

import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.dto.quotationdetail.QuotationDetailRequestDTO;
import com.example.evsalesmanagement.enums.QuoteStatusEnum;

public class QuoteRequestDTO {

    // private Integer employeeId;
    private Integer customerId;

    private QuoteStatusEnum status;

    private List<QuotationDetailRequestDTO> quotationDetailRequestDTOs = new ArrayList<>();


    public QuoteStatusEnum getStatus() {
        return status;
    }

    public void setStatus(QuoteStatusEnum status) {
        this.status = status;
    }

    public List<QuotationDetailRequestDTO> getQuotationDetailRequestDTOs() {
        return quotationDetailRequestDTOs;
    }

    public void setQuotationDetailRequestDTOs(List<QuotationDetailRequestDTO> quotationDetailRequestDTOs) {
        this.quotationDetailRequestDTOs = quotationDetailRequestDTOs;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}
