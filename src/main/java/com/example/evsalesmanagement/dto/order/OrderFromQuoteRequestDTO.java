package com.example.evsalesmanagement.dto.order;

public class OrderFromQuoteRequestDTO {

    private Integer customerId;

    private Integer employeeId;

    private Integer agencyId;

    private Integer quoteId;

    private String notes;

    public OrderFromQuoteRequestDTO() {
    }

    public OrderFromQuoteRequestDTO(Integer customerId, Integer employeeId, Integer agencyId, Integer quoteId,
            String notes) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.agencyId = agencyId;
        this.quoteId = quoteId;
        this.notes = notes;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
