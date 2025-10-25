package com.example.evsalesmanagement.model;

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

@Entity
@Table(name = "VehicleDelivery")
public class VehicleDelivery extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleDeliveryId")
    private Integer vehicleDeliveryId;

    // @Column(name = "CreatedDate")
    // private LocalDateTime createdDate;

    @Column(name = "ExpectedDeliveryDate")
    private LocalDateTime expectedDeliveryDate;

    @Column(name = "ActualDeliveryDate")
    private LocalDateTime actualDeliveryDate;

    @Column(name = "Status")
    private String status;

    @OneToOne
    @JoinColumn(name = "OrderId", unique = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    public Integer getVehicleDeliveryId() {
        return vehicleDeliveryId;
    }

    public void setVehicleDeliveryId(Integer vehicleDeliveryId) {
        this.vehicleDeliveryId = vehicleDeliveryId;
    }

    // public LocalDateTime getCreatedDate() {
    //     return createdDate;
    // }

    // public void setCreatedDate(LocalDateTime createdDate) {
    //     this.createdDate = createdDate;
    // }

    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public LocalDateTime getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDateTime actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
