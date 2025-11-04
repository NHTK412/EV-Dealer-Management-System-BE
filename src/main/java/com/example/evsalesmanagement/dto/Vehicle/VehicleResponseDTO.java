package com.example.evsalesmanagement.dto.Vehicle;



import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.model.Vehicle;

public class VehicleResponseDTO{
    private String chassisNumber;
    private String machineNumber;
    private String status;
    private String vehicleCondition;
    private VehicleTypeDetailDTO vehicleTypeDetail;
    private AgencyDTO agency;

    public VehicleResponseDTO() {
    }

    public VehicleResponseDTO(Vehicle vehicle) {
        this.chassisNumber = vehicle.getChassicNumber();
        this.machineNumber = vehicle.getMachineNumber();
        this.status = vehicle.getStatus();
        this.vehicleCondition = vehicle.getVehicleCondition();
       
    }

    public VehicleResponseDTO(String chassisNumber, String machineNumber, String status, String vehicleCondition,
            VehicleTypeDetailDTO vehicleTypeDetail, AgencyDTO agency) {
        this.chassisNumber = chassisNumber;
        this.machineNumber = machineNumber;
        this.status = status;
        this.vehicleCondition = vehicleCondition;
        this.vehicleTypeDetail = vehicleTypeDetail;
        this.agency = agency;
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

    

    


    
