
package com.example.evsalesmanagement.dto.vehicleTypeDetailDTO;

import com.example.evsalesmanagement.model.VehicleTypeDetail;

import java.math.BigDecimal;

public class VehicleTypeDetailResponseDTO {
    public VehicleTypeDetailResponseDTO() {}

    public VehicleTypeDetailResponseDTO(VehicleTypeDetail entity) {
        this.vehicleTypeDetailId = entity.getVehicleTypeDetailId();
        this.vehicleImage = entity.getVehicleImage();
        this.configuration = entity.getConfiguration();
        this.color = entity.getColor();
        this.version = entity.getVersion();
        this.features = entity.getFeatures();
        this.price = entity.getPrice();
        this.vehicleTypeId = entity.getVehicleType() != null ? entity.getVehicleType().getVehicleTypeId() : null;
    }
    private Integer vehicleTypeDetailId;
    private String vehicleImage;
    private String configuration;
    private String color;
    private String version;
    private String features;
    private BigDecimal price;
    private Integer vehicleTypeId;
    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }
    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }
    public String getVehicleImage() {
        return vehicleImage;
    }
    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }
    public String getConfiguration() {
        return configuration;
    }
    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }
    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

   
}
