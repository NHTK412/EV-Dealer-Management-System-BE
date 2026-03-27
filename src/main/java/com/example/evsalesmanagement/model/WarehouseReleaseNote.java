package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.evsalesmanagement.enums.WarehouseReleaseNoteStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "WarehouseReleaseNote")
public class WarehouseReleaseNote extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WarehouseReleaseNoteId")
    private Integer warehouseReleaseNoteId;

    @Column(name = "ReleaseDate")
    private LocalDateTime releaseDate;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "Note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private WarehouseReleaseNoteStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employeeId;

    @OneToOne
    @JoinColumn(name = "OrderId", unique = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agencyId;



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


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    
    @ManyToMany
    @JoinTable(name = "WarehouseReleaseNoteDetail", joinColumns = @JoinColumn(name = "WarehouseReleaseNoteId"), inverseJoinColumns = @JoinColumn(name = "VehicleId"))
    private Set<Vehicle> vehicles = new HashSet<>();

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles2) {
        this.vehicles = vehicles2;
    }

    public WarehouseReleaseNoteStatusEnum getStatus() {
        return status;
    }

    public void setStatus(WarehouseReleaseNoteStatusEnum status) {
        this.status = status;
    }

    public Agency getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Agency agencyId) {
        this.agencyId = agencyId;
    }

}