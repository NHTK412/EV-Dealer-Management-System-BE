package com.example.evsalesmanagement.dto.salesdiscountlevel;

import java.math.BigDecimal;

public class SalesDiscountLevelRequestDTO {
    private BigDecimal salesFrom;
    private BigDecimal salesTo;
    private BigDecimal discountPercentage;

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
