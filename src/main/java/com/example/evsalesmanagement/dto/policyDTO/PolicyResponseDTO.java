package com.example.evsalesmanagement.dto.policyDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PolicyResponseDTO {
    private Integer policyId;
    private String policyType;
    private BigDecimal policyValue;
    private String policyCondition;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private Integer agencyId;
    private List<QuantityDiscountLevelResponseDTO> quantityDiscountLevels;
    private List<SalesDiscountLevelResponseDTO> salesDiscountLevels;
    public Integer getPolicyId() {
        return policyId;
    }
    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }
    public String getPolicyType() {
        return policyType;
    }
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }
    public BigDecimal getPolicyValue() {
        return policyValue;
    }
    public void setPolicyValue(BigDecimal policyValue) {
        this.policyValue = policyValue;
    }
    public String getPolicyCondition() {
        return policyCondition;
    }
    public void setPolicyCondition(String policyCondition) {
        this.policyCondition = policyCondition;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getAgencyId() {
        return agencyId;
    }
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
    public List<QuantityDiscountLevelResponseDTO> getQuantityDiscountLevels() {
        return quantityDiscountLevels;
    }
    public void setQuantityDiscountLevels(List<QuantityDiscountLevelResponseDTO> quantityDiscountLevels) {
        this.quantityDiscountLevels = quantityDiscountLevels;
    }
    public List<SalesDiscountLevelResponseDTO> getSalesDiscountLevels() {
        return salesDiscountLevels;
    }
    public void setSalesDiscountLevels(List<SalesDiscountLevelResponseDTO> salesDiscountLevels) {
        this.salesDiscountLevels = salesDiscountLevels;
    }
    
    
}
