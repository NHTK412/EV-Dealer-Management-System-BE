package com.example.evsalesmanagement.dto.importRequestDetail;

import com.example.evsalesmanagement.model.ImportRequestDetail;

public class ImportRequestDetailResponseDTO {
    private Integer vehicleTypeDetailId;

    private String VehicleTypeName;

    private String configuration;

    private String color;

    private String version;

    private String features;

    private Integer quantity;

    public ImportRequestDetailResponseDTO(ImportRequestDetail importRequestDetail) {

        this.vehicleTypeDetailId = importRequestDetail.getVehicleTypeDetail().getVehicleTypeDetailId();

        this.VehicleTypeName = importRequestDetail.getVehicleTypeDetail().getVehicleType().getVehicleTypeName();

        this.configuration = importRequestDetail.getVehicleTypeDetail().getConfiguration();

        this.color = importRequestDetail.getVehicleTypeDetail().getColor();

        this.version = importRequestDetail.getVehicleTypeDetail().getVersion();

        this.features = importRequestDetail.getVehicleTypeDetail().getFeatures();

        this.quantity = importRequestDetail.getQuantity();

    }

    public ImportRequestDetailResponseDTO(Integer vehicleTypeDetailId, String vehicleTypeName, String configuration,
            String color,
            String version, String features, Integer quantity) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
        this.VehicleTypeName = vehicleTypeName;
        this.configuration = configuration;
        this.color = color;
        this.version = version;
        this.features = features;
        this.quantity = quantity;
    }

    public ImportRequestDetailResponseDTO() {
    }

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }

    public String getVehicleTypeName() {
        return VehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        VehicleTypeName = vehicleTypeName;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
