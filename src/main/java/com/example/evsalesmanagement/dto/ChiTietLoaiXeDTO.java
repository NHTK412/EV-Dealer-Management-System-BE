package com.example.evsalesmanagement.dto;

import java.math.BigDecimal;

import com.example.evsalesmanagement.model.VehicleTypeDetail;

public class VehicleTypeDetailSummaryDTO {

    private Integer vehicleTypeDetailId;
    private String vehicleImage;
    private String configuration;
    private String color;
    private String version;
    private String feature;
    private BigDecimal price;

    public VehicleTypeDetailSummaryDTO(VehicleTypeDetail vehicleTypeDetail) {
        this.vehicleTypeDetailId = vehicleTypeDetail.getVehicleTypeDetailId();
        this.vehicleImage = vehicleTypeDetail.getVehicleImage();
        this.configuration = vehicleTypeDetail.getConfiguration();
        this.color = vehicleTypeDetail.getColor();
        this.version = vehicleTypeDetail.getVersion();
        this.feature = vehicleTypeDetail.getFeature();
        this.price = vehicleTypeDetail.getPrice();
    }

    public VehicleTypeDetailSummaryDTO(Integer vehicleTypeDetailId, String vehicleImage, String configuration,
            String color, String version, String feature, BigDecimal price) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
        this.vehicleImage = vehicleImage;
        this.configuration = configuration;
        this.color = color;
        this.version = version;
        this.feature = feature;
        this.price = price;
    }

    public VehicleTypeDetailSummaryDTO() {
    }

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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
