package com.example.evsalesmanagement.dto.policy;

import java.math.BigDecimal;

public class SalesDiscountLevelResponseDTO {
    private Integer salesDiscountLevelId;
    private Integer salesFrom;
    private Integer salesTo;
    private BigDecimal discountPercentage;

    public Integer getSalesDiscountLevelId() {
        return salesDiscountLevelId;
    }
    public void setSalesDiscountLevelId(Integer salesDiscountLevelId) {
        this.salesDiscountLevelId = salesDiscountLevelId;
    }
    public Integer getSalesFrom() {
        return salesFrom;
    }
    public void setSalesFrom(Integer salesFrom) {
        this.salesFrom = salesFrom;
    }
    public Integer getSalesTo() {
        return salesTo;
    }
    public void setSalesTo(Integer salesTo) {
        this.salesTo = salesTo;
    }
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }
    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    
}
