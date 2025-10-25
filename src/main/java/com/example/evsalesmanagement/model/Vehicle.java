package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vehicle")
public class Vehicle extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleId")
    private Integer vehicleId;

    @Column(name = "ChassisNumber", unique = true)
    private String chassisNumber;

    @Column(name = "EngineNumber", unique = true)
    private String engineNumber;

    @Column(name = "Status")
    private String status;

    @Column(name = "VehicleCondition")
    private String vehicleCondition;

    @ManyToOne
    @JoinColumn(name = "VehicleDetailId")
    private VehicleTypeDetail vehicleDetail;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agency;

    // @OneToMany(mappedBy = "vehicle")
    // private List<TestDriveSchedule> testDriveSchedules = new ArrayList<>();

    // @OneToMany(mappedBy = "vehicle")
    // private List<DeliveryDetail> deliveryDetails = new ArrayList<>();

    // @OneToMany(mappedBy = "vehicle")
    // private List<ImportDetail> importDetails = new ArrayList<>();

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

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
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

    public VehicleTypeDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(VehicleTypeDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
