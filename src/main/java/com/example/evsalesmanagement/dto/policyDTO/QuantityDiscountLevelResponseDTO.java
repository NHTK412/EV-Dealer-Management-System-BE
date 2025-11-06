package com.example.evsalesmanagement.dto.policyDTO;

import java.math.BigDecimal;

public class QuantityDiscountLevelResponseDTO {
    private Integer quantityDiscountLevelId;
    private Integer quantityFrom;
    private Integer quantityTo;
    private BigDecimal discountPercentage;

    public Integer getQuantityDiscountLevelId() {
        return quantityDiscountLevelId;
    }
    public void setQuantityDiscountLevelId(Integer quantityDiscountLevelId) {
        this.quantityDiscountLevelId = quantityDiscountLevelId;
    }
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
