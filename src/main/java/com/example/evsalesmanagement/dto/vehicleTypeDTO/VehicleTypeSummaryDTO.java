
package com.example.evsalesmanagement.dto.vehicleTypeDTO;

import com.example.evsalesmanagement.model.VehicleType;

public class VehicleTypeSummaryDTO {
    private Integer vehicleTypeId;
    private String vehicleTypeName;

    public VehicleTypeSummaryDTO() {}

    public VehicleTypeSummaryDTO(VehicleType entity) {
        this.vehicleTypeId = entity.getVehicleTypeId();
        this.vehicleTypeName = entity.getVehicleTypeName();
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

}
