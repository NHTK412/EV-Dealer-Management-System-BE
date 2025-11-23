package com.example.evsalesmanagement.dto.vehicle;

import com.example.evsalesmanagement.dto.agency.AgencyResponseDTO;
import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
import com.example.evsalesmanagement.enums.VehicleStatusEnum;
import com.example.evsalesmanagement.model.Vehicle;

public class VehicleResponseDTO {

    private Integer vehicleId;
    private String chassisNumber;
    private String machineNumber;
    private VehicleStatusEnum status;
    private String vehicleCondition;
    private VehicleTypeDetailResponseDTO vehicleTypeDetail;
    private AgencyResponseDTO agency; 

    public VehicleResponseDTO() {}

    public VehicleResponseDTO(Vehicle vehicle) {
        this.vehicleId = vehicle.getVehicleId();
        this.chassisNumber = vehicle.getChassicNumber();
        this.machineNumber = vehicle.getMachineNumber();
        this.status = vehicle.getStatus();
        this.vehicleCondition = vehicle.getVehicleCondition();

        if (vehicle.getVehicleTypeDetail() != null) {
            this.vehicleTypeDetail = new VehicleTypeDetailResponseDTO(vehicle.getVehicleTypeDetail());
        }

        if (vehicle.getAgency() != null) {
            this.agency = new AgencyResponseDTO(vehicle.getAgency());
        }
    }

    // Getter & Setter
    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
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

    public VehicleTypeDetailResponseDTO getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleTypeDetailResponseDTO vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }

    public AgencyResponseDTO getAgency() {
        return agency;
    }

    public void setAgency(AgencyResponseDTO agency) {
        this.agency = agency;
    }
}
