package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//PhieuNhapKho = WarehouseReceipt
@Entity
@Table(name = "WarehouseReceipt")
public class WarehouseReceipt extends TimeStampRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WarehouseReceiptId")
    private Integer warehouseReceiptId;

    @Column(name = "WarehouseReceiptDate")
    private LocalDateTime warehouseReceiptDate;
 
    // Season -ly do
    @Column(name = "Reason")
    private String lyDo;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "Note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agencyId;

    
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

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
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

   
}