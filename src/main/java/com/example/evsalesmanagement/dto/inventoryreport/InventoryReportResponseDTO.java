package com.example.evsalesmanagement.dto.inventoryreport;

import java.math.BigDecimal;

public class InventoryReportResponseDTO {
    private Integer vehicleTypeId;
    private String vehicleTypeName;
    private Integer manufactureYear;
    private Integer vehicleTypeDetailId;
    private String version;
    private String color;
    private BigDecimal price;
    private String agencyName;
    private Long totalQuantity;
    private BigDecimal totalValue;


    public InventoryReportResponseDTO(Integer vehicleTypeId, String vehicleTypeName, Integer manufactureYear,
                              Integer vehicleTypeDetailId, String version, String color,
                              BigDecimal price, String agencyName, Long totalQuantity) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.manufactureYear = manufactureYear;
        this.vehicleTypeDetailId = vehicleTypeDetailId;
        this.version = version;
        this.color = color;
        this.price = price;
        this.agencyName = agencyName;
        this.totalQuantity = totalQuantity;
        this.totalValue = price != null && totalQuantity != null 
            ? price.multiply(BigDecimal.valueOf(totalQuantity)) 
            : BigDecimal.ZERO;
    }


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

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
        this.totalValue = price != null && totalQuantity != null 
            ? price.multiply(BigDecimal.valueOf(totalQuantity)) 
            : BigDecimal.ZERO;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}