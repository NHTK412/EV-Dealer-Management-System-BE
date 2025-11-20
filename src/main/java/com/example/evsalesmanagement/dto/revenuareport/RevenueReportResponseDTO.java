package com.example.evsalesmanagement.dto.revenuareport;

import java.math.BigDecimal;

public class RevenueReportResponseDTO {
    private Integer vehicleTypeId;
    private String vehicleTypeName;
    private Integer vehicleTypeDetailId;
    private String version;
    private String color;
    private String agencyName;
    private Integer totalOrders;        
    private Integer totalQuantity;      
    private BigDecimal totalRevenue;    
    private BigDecimal totalDiscount;   
    private BigDecimal netRevenue;     

    // Constructor
    public RevenueReportResponseDTO(Integer vehicleTypeId, String vehicleTypeName,
                                    Integer vehicleTypeDetailId, String version, String color,
                                    String agencyName, Integer totalOrders, Integer totalQuantity,
                                    BigDecimal totalRevenue, BigDecimal totalDiscount, BigDecimal netRevenue) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.vehicleTypeDetailId = vehicleTypeDetailId;
        this.version = version;
        this.color = color;
        this.agencyName = agencyName;
        this.totalOrders = totalOrders;
        this.totalQuantity = totalQuantity;
        
        // FIX: Null-safe operations
        this.totalRevenue = totalRevenue != null ? totalRevenue : BigDecimal.ZERO;
        this.totalDiscount = totalDiscount != null ? totalDiscount : BigDecimal.ZERO;
        this.netRevenue = this.totalRevenue.subtract(this.totalDiscount);
    }
    
    // Constructor - no netRevenue
    public RevenueReportResponseDTO(Integer vehicleTypeId, String vehicleTypeName,
                                    Integer vehicleTypeDetailId, String version, String color,
                                    String agencyName, Integer totalOrders, Integer totalQuantity,
                                    BigDecimal totalRevenue, BigDecimal totalDiscount) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.vehicleTypeDetailId = vehicleTypeDetailId;
        this.version = version;
        this.color = color;
        this.agencyName = agencyName;
        this.totalOrders = totalOrders;
        this.totalQuantity = totalQuantity;
        
        // FIX: Null-safe operations
        this.totalRevenue = totalRevenue != null ? totalRevenue : BigDecimal.ZERO;
        this.totalDiscount = totalDiscount != null ? totalDiscount : BigDecimal.ZERO;
        this.netRevenue = this.totalRevenue.subtract(this.totalDiscount);
    }

    public RevenueReportResponseDTO() {
        // FIX: Initialize with ZERO to prevent null
        this.totalRevenue = BigDecimal.ZERO;
        this.totalDiscount = BigDecimal.ZERO;
        this.netRevenue = BigDecimal.ZERO;
    }

    // Getters and Setters
    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        // FIX: Null-safe setter
        this.totalRevenue = totalRevenue != null ? totalRevenue : BigDecimal.ZERO;
        
        // Recalculate netRevenue
        BigDecimal safeDiscount = this.totalDiscount != null ? this.totalDiscount : BigDecimal.ZERO;
        this.netRevenue = this.totalRevenue.subtract(safeDiscount);
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        // FIX: Null-safe setter
        this.totalDiscount = totalDiscount != null ? totalDiscount : BigDecimal.ZERO;
        
        // Recalculate netRevenue
        BigDecimal safeRevenue = this.totalRevenue != null ? this.totalRevenue : BigDecimal.ZERO;
        this.netRevenue = safeRevenue.subtract(this.totalDiscount);
    }

    public BigDecimal getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(BigDecimal netRevenue) {
        this.netRevenue = netRevenue != null ? netRevenue : BigDecimal.ZERO;
    }
}