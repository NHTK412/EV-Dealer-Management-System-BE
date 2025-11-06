
package com.example.evsalesmanagement.dto.vehicletype;

import com.example.evsalesmanagement.model.VehicleType;

public class VehicleTypeResponseDTO {
    private Integer vehicleTypeId;
    private String vehicleTypeName;
    private Integer manufactureYear;
    private String description;

    public VehicleTypeResponseDTO() {}

    public VehicleTypeResponseDTO(VehicleType entity) {
        this.vehicleTypeId = entity.getVehicleTypeId();
        this.vehicleTypeName = entity.getVehicleTypeName();
        this.manufactureYear = entity.getManufactureYear();
        this.description = entity.getDescription();
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
