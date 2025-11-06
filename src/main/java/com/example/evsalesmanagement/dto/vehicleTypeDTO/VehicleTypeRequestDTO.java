package com.example.evsalesmanagement.dto.vehicleTypeDTO;

public class VehicleTypeRequestDTO {
    private String vehicleTypeName;
    private Integer manufactureYear;
    private String description;
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
