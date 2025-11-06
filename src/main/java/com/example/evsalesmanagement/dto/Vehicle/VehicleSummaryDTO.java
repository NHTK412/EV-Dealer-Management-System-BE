package com.example.evsalesmanagement.dto.vehicle;

import com.example.evsalesmanagement.model.Vehicle;

public class VehicleSummaryDTO {
    private String chassisNumber;
    private String machineNumber;
    private String status;
    private String vehicleCondition;

    public VehicleSummaryDTO() {
    }

    public VehicleSummaryDTO(Vehicle vehicle) {
        this.chassisNumber = vehicle.getChassicNumber();
        this.machineNumber = vehicle.getMachineNumber();
        this.status = vehicle.getStatus();
        this.vehicleCondition = vehicle.getVehicleCondition();
    }

    public VehicleSummaryDTO(String chassisNumber, String machineNumber, String status, String vehicleCondition) {
        this.chassisNumber = chassisNumber;
        this.machineNumber = machineNumber;
        this.status = status;
        this.vehicleCondition = vehicleCondition;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getVehicleCondition() {
        return vehicleCondition;
    }
    
    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }
   
    
    
}
