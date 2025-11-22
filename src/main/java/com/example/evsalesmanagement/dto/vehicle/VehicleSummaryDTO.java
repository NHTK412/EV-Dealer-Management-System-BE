package com.example.evsalesmanagement.dto.vehicle;

import com.example.evsalesmanagement.enums.VehicleStatusEnum;
import com.example.evsalesmanagement.model.Vehicle;

public class VehicleSummaryDTO {
    private String chassisNumber;
    private String machineNumber;
    private VehicleStatusEnum status;
    private String vehicleCondition;
    private Integer id;

    public VehicleSummaryDTO() {
    }

    public VehicleSummaryDTO(Vehicle vehicle) {
        this.id= vehicle.getVehicleId();
        this.chassisNumber = vehicle.getChassicNumber();
        this.machineNumber = vehicle.getMachineNumber();
        this.status = vehicle.getStatus();
        this.vehicleCondition = vehicle.getVehicleCondition();
    }

    public VehicleSummaryDTO(Integer id, String chassisNumber, String machineNumber, VehicleStatusEnum status,
            String vehicleCondition) {
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.machineNumber = machineNumber;
        this.status = status;
        this.vehicleCondition = vehicleCondition;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public VehicleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(VehicleStatusEnum status) {
        this.status = status;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

}
