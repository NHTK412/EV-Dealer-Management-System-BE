package com.example.evsalesmanagement.dto.policy;

import java.math.BigDecimal;

public class SalesDiscountLevelRequestDTO {
    private Integer salesFrom;
    private Integer salesTo;
    private BigDecimal discountPercentage;
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
