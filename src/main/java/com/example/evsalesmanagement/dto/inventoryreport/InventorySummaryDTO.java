package com.example.evsalesmanagement.dto.inventoryreport;

public class InventorySummaryDTO {

    private Integer vehicleTypeDetailId;

    private String vehicleTypeName;

    private String vehicleTypeDetailName;

    private Long totalQuantity;

    private String version;

    private String color;

    private String features;

    public InventorySummaryDTO() {
    }

    public InventorySummaryDTO(Integer vehicleTypeDetailId,
            String vehicleTypeName,
            String vehicleTypeDetailName,
            Long totalQuantity,
            String version,
            String color,
            String features) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
        this.vehicleTypeName = vehicleTypeName;
        this.vehicleTypeDetailName = vehicleTypeDetailName;
        this.totalQuantity = totalQuantity;
        this.version = version;
        this.color = color;
        this.features = features;
    }

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVehicleTypeDetailName() {
        return vehicleTypeDetailName;
    }

    public void setVehicleTypeDetailName(String vehicleTypeDetailName) {
        this.vehicleTypeDetailName = vehicleTypeDetailName;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
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

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

}
