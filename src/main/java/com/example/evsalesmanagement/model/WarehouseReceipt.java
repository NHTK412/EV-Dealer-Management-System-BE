package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//PhieuNhapKho = WarehouseReceipt
@Entity
@Table(name = "WarehouseReceipt")
public class WarehouseReceipt extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WarehouseReceiptId")
    private Integer warehouseReceiptId;

    @Column(name = "WarehouseReceiptDate")
    private LocalDateTime warehouseReceiptDate;

    // Season -ly do
    @Column(name = "Reason")
    private String reason;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "Note")
    private String note;

    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agencyId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWarehouseReceiptId() {
        return warehouseReceiptId;
    }

    public void setWarehouseReceiptId(Integer warehouseReceiptId) {
        this.warehouseReceiptId = warehouseReceiptId;
    }

    public LocalDateTime getWareHouseReceiptDate() {
        return warehouseReceiptDate;
    }

    public void setWareHouseReceiptDate(LocalDateTime wareHouseReceiptDate) {
        this.warehouseReceiptDate = wareHouseReceiptDate;
    }

    public LocalDateTime getWarehouseReceiptDate() {
        return warehouseReceiptDate;
    }

    public void setWarehouseReceiptDate(LocalDateTime warehouseReceiptDate) {
        this.warehouseReceiptDate = warehouseReceiptDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Agency getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Agency agencyId) {
        this.agencyId = agencyId;
    }

    // @OneToMany(mappedBy = "phieuNhapKho")
    // private List<ChiTietPhieuNhap> chiTietPhieuNhaps = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "WarehouseReceiptDetail", joinColumns = @JoinColumn(name = "WarehouseReceiptId"), inverseJoinColumns = @JoinColumn(name = "VehicleId"))
    private List<Vehicle> vehicles;
}