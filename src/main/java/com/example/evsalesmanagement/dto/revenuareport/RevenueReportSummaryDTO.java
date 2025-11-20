package com.example.evsalesmanagement.dto.revenuareport;

import java.math.BigDecimal;

public class RevenueReportSummaryDTO {
    private BigDecimal totalRevenue;
    private BigDecimal totalDiscount;
    private BigDecimal netRevenue;
    private Integer totalOrders;
    private Integer totalQuantity;

    public RevenueReportSummaryDTO(BigDecimal totalRevenue, BigDecimal totalDiscount,
                                   Integer totalOrders,  Integer totalQuantity) {
        this.totalRevenue = totalRevenue != null ? totalRevenue : BigDecimal.ZERO;
        this.totalDiscount = totalDiscount != null ? totalDiscount : BigDecimal.ZERO;
        this.netRevenue = this.totalRevenue.subtract(this.totalDiscount);
        this.totalOrders = totalOrders != null ? totalOrders : 0;
        this.totalQuantity = totalQuantity != null ? totalQuantity : 0;
    }

    // Getters and Setters
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public BigDecimal getNetRevenue() {
        return netRevenue;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public void setNetRevenue(BigDecimal netRevenue) {
        this.netRevenue = netRevenue;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
