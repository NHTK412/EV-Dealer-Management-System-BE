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
@Table(name = "Xe")
public class Vehicle extends TimeStampRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleId")
    private Integer vehicleId;

    //so khung = chassisNumber
    @Column(name = "ChassisNumber", unique = true)
    private String chassicNumber;

    //so may = machineNumber
    @Column(name = "MachineNumber", unique = true)
    private String machineNumber;

    //trang thai = status
    @Column(name = "Status")
    private String status;

    //tinh trang xe = vehicleCondition
    @Column(name = "VehicleCondition")
    private String vehicleCondition;

    //chi tiet loai xe = vehicleTypeDetail
    @ManyToOne
    @JoinColumn(name = "VehicleTypeDetailId")
    private VehicleTypeDetail vehicleTypeDetail;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agency;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getChassicNumber() {
        return chassicNumber;
    }

    public void setChassicNumber(String chassicNumber) {
        this.chassicNumber = chassicNumber;
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

    public VehicleTypeDetail getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleTypeDetail vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    // @OneToMany(mappedBy = "xe")
    // private List<LichHenLaiThu> lichHenLaiThus = new ArrayList<>();

    // @OneToMany(mappedBy = "xe")
    // private List<ChiTietPhieuXuat> chiTietPhieuXuats = new ArrayList<>();

    // @OneToMany(mappedBy = "xe")
    // private List<ChiTietPhieuNhap> chiTietPhieuNhaps = new ArrayList<>();

    
}