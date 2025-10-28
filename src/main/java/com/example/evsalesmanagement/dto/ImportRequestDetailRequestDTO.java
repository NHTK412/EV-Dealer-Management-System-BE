package com.example.evsalesmanagement.dto;


public class ImportRequestDetailRequestDTO {

    private Integer vehicleTypeDetailId ;

    private Integer quantity;
    
    public ImportRequestDetailRequestDTO() {
    }

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
 
    public ImportRequestDetailRequestDTO(Integer vehicleTypeDetailId, Integer quantity) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
        this.quantity = quantity;
    }

}
