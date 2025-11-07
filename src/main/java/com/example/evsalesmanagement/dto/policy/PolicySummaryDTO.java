package com.example.evsalesmanagement.dto.policy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.PolicyStatusEnum;
import com.example.evsalesmanagement.enums.PolicyTypeEnum;

public class PolicySummaryDTO {
    private Integer policyId;
    private PolicyTypeEnum policyType;
    private BigDecimal policyValue;
    private String policyCondition;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PolicyStatusEnum status;
    public Integer getPolicyId() {
        return policyId;
    }
    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }
    public PolicyTypeEnum getPolicyType() {
        return policyType;
    }
    public void setPolicyType(PolicyTypeEnum policyType) {
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
    public PolicyStatusEnum getStatus() {
        return status;
    }
    public void setStatus(PolicyStatusEnum status) {
        this.status = status;
    }
    
    
}
