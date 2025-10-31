package com.example.evsalesmanagement.dto;

import java.math.BigDecimal;

import com.example.evsalesmanagement.model.VehicleTypeDetail;

public class VehicleTypeDetailDTO {
    
    private Integer vehicleTypeDetailId;

    private String vehicleImage;

    private String configuration;

    private String color;

    private String version;
    // tinh nang = features)
    private String features;

    private BigDecimal price;

    private Integer vehicleTypeId;


    public VehicleTypeDetailDTO(VehicleTypeDetail vehicleTypeDetail){
        this.vehicleTypeDetailId = vehicleTypeDetail.getVehicleTypeDetailId();
        this.vehicleImage = vehicleTypeDetail.getVehicleImage();
        this.configuration = vehicleTypeDetail.getConfiguration();
        this.color = vehicleTypeDetail.getColor();
        this.version = vehicleTypeDetail.getVersion();
        this.features = vehicleTypeDetail.getFeatures();
        this.price = vehicleTypeDetail.getPrice();
         this.vehicleTypeId = vehicleTypeDetail.getVehicleType() != null ? vehicleTypeDetail.getVehicleType().getVehicleTypeId() : null;
    }
  
        

    public VehicleTypeDetailDTO(Integer vehicleTypeDetailId, String vehicleImage, String configuration, String color,
            String version, String features, BigDecimal price, Integer vehicleTypeId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
        this.vehicleImage = vehicleImage;
        this.configuration = configuration;
        this.color = color;
        this.version = version;
        this.features = features;
        this.price = price;
        this.vehicleTypeId = vehicleTypeId;
    }

    public VehicleTypeDetailDTO() {
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