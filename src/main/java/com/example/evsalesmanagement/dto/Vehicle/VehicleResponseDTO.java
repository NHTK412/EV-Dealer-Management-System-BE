package com.example.evsalesmanagement.dto.vehicle;



import com.example.evsalesmanagement.dto.vehicletypedetail.VehicleTypeDetailResponseDTO;
import com.example.evsalesmanagement.model.Vehicle;

public class VehicleResponseDTO{
    private Integer vehicleId;
    private String chassisNumber;
    private String machineNumber;
    private String status;
    private String vehicleCondition;
    private VehicleTypeDetailResponseDTO vehicleTypeDetail;
    private Integer agencyId;

    public Integer getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleResponseDTO() {
    }


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
            this.agencyId = vehicle.getAgency().getAgencyId();
        }
    }

    public VehicleResponseDTO(String chassisNumber, String machineNumber, String status, String vehicleCondition,
            VehicleTypeDetailResponseDTO vehicleTypeDetail, Integer agencyId) {
        this.chassisNumber = chassisNumber;
        this.machineNumber = machineNumber;
        this.status = status;
        this.vehicleCondition = vehicleCondition;
        this.vehicleTypeDetail = vehicleTypeDetail;
        this.agencyId = agencyId;
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

    public VehicleTypeDetailResponseDTO getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleTypeDetailResponseDTO vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }

    // Đã loại bỏ getter/setter cho AgencyDTO, chỉ trả về agencyId
    public Integer getAgencyId() {
        return agencyId;
    }
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    
    


}

    

    


    
