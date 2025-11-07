package com.example.evsalesmanagement.dto.salesdiscountlevel;

import java.math.BigDecimal;

public class SalesDiscountLevelResponseDTO {
    private Integer salesDiscountLevelId;
    private BigDecimal salesFrom;
    private BigDecimal salesTo;
    private BigDecimal discountPercentage;

    public Integer getSalesDiscountLevelId() {
        return salesDiscountLevelId;
    }
    public void setSalesDiscountLevelId(Integer salesDiscountLevelId) {
        this.salesDiscountLevelId = salesDiscountLevelId;
    }
    public BigDecimal getSalesFrom() {
        return salesFrom;
    }
    public void setSalesFrom(BigDecimal salesFrom) {
        this.salesFrom = salesFrom;
    }
    public BigDecimal getSalesTo() {
        return salesTo;
    }
    public void setSalesTo(BigDecimal salesTo) {
        this.salesTo = salesTo;
    }
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }
    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    
}
