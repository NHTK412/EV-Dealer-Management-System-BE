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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//PhieuXuatKho = WarehouseReleaseNote
@Entity
@Table(name = "WarehouseReleaseNote")
public class WarehouseReleaseNote extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WarehouseReleaseNoteId")
    private Integer warehouseReleaseNoteId;

    @Column(name = "ReleaseDate")
    private LocalDateTime releaseDate;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "Note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employeeId;

    @OneToOne
    @JoinColumn(name = "OrderId", unique = true)
    private Order order;

    public Integer getWarehouseReleaseNoteId() {
        return warehouseReleaseNoteId;
    }

    public void setWarehouseReleaseNoteId(Integer warehouseReleaseNoteId) {
        this.warehouseReleaseNoteId = warehouseReleaseNoteId;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
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

    public Order getOder() {
        return order;
    }

    public void setOder(Order order) {
        this.order = order;
    }

    // @OneToMany(mappedBy = "phieuXuatKho")
    // private List<ChiTietPhieuXuat> chiTietPhieuXuats = new ArrayList<>();

    
    
}