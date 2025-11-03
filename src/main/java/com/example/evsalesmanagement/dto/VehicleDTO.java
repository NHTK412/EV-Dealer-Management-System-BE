package com.example.evsalesmanagement.dto;


import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.model.VehicleTypeDetail;


public class VehicleDTO {
    private String chassisNumber;
    private String machineNumber;
    private String status;
    private String vehicleCondition;
    private Integer vehicleTypeDetailId;
    private Integer agencyId;
    private VehicleTypeDetailDTO vehicleTypeDetail;
    private AgencyDTO agency;

    
    public VehicleDTO(Vehicle vehicle) {
        this.chassisNumber = vehicle.getChassicNumber();
        this.machineNumber = vehicle.getMachineNumber();
        this.status = vehicle.getStatus();
        this.vehicleCondition = vehicle.getVehicleCondition();
        VehicleTypeDetail detail = vehicle.getVehicleTypeDetail();
        if (detail != null) {
            this.vehicleTypeDetail = new VehicleTypeDetailDTO(detail);
        }

        Agency agency = vehicle.getAgency();
        if (agency != null) {
            this.agency = new AgencyDTO(agency);
        }
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

    public VehicleTypeDetailDTO getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleTypeDetailDTO vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }

    public AgencyDTO getAgency() {
        return agency;
    }

    public void setAgency(AgencyDTO agency) {
        this.agency = agency;
    }

    
}