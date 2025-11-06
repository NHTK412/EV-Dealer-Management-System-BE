package com.example.evsalesmanagement.dto.policyDTO;

import java.math.BigDecimal;

public class QuantityDiscountLevelRequestDTO {
    private Integer quantityFrom;
    private Integer quantityTo;
    private BigDecimal discountPercentage;
    public Integer getQuantityFrom() {
        return quantityFrom;
    }
    public void setQuantityFrom(Integer quantityFrom) {
        this.quantityFrom = quantityFrom;
    }
    public Integer getQuantityTo() {
        return quantityTo;
    }
    public void setQuantityTo(Integer quantityTo) {
        this.quantityTo = quantityTo;
    }
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }
    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    
}
