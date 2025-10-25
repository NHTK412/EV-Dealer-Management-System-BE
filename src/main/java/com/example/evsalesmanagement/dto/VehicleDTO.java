package com.example.evsalesmanagement.dto;


import com.example.evsalesmanagement.model.Vehicle;


public class VehicleDTO {
    private String chassisNumber;
    private String machineNumber;
    private String status;
    private String vehicleCondition;
    private Integer vehicleTypeDetailId;
    private Integer agencyId;
    
    public VehicleDTO(Vehicle vehicle) {
        this.chassisNumber = vehicle.getChassicNumber();
        this.machineNumber = vehicle.getMachineNumber();
        this.status = vehicle.getStatus();
        this.vehicleCondition = vehicle.getVehicleCondition();
        this.vehicleTypeDetailId = vehicle.getVehicleTypeDetail() != null ? vehicle.getVehicleTypeDetail().getVehicleTypeDetailId() : null;
        this.agencyId = vehicle.getAgency() != null ? vehicle.getAgency().getAgencyId() : null;
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

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

}