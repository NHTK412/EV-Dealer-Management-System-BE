package com.example.evsalesmanagement.dto;

import com.example.evsalesmanagement.model.VehicleType;



public class VehicleTypeDTO {
    private Integer vehicleTypeId;

    private String vehicleTypeName;
    // nam san xuat = manufactureYear
    private Integer manufactureYear;
    // mo ta = description
    private String description;

    public VehicleTypeDTO(VehicleType vehicleType){
        this.vehicleTypeId = vehicleType.getVehicleTypeId();
        this.vehicleTypeName = vehicleType.getVehicleTypeName();
        this.manufactureYear = vehicleType.getManufactureYear();
        this.description = vehicleType.getDescription();
        }
  
        

    public VehicleTypeDTO(Integer vehicleTypeId, String vehicleTypeName, Integer manufactureYear, String description){
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.manufactureYear = manufactureYear;
        this.description = description;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

 

}