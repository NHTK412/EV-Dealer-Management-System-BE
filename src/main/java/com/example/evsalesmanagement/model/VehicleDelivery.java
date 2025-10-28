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
public class VehicleDelivery extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleDeliveryId")
    private Integer vehicleDeliveryId;

    // @Column(name = "NgayTaoDon")
    // private LocalDateTime ngayTaoDon;

    //ngayDuKienGiaoXe = ExpectedDeliveryDate
    @Column(name = "ExpectedDeliveryDate")
    private LocalDateTime ExpectedDeliveryDate;

    //ngayGiaoXe = DeliveryDate
    @Column(name = "DeliveryDate")
    private LocalDateTime deliveryDate;


    @Column(name = "Status")
    private String status;

    @OneToOne
    @JoinColumn(name = "OrderId", unique = true)
    private Order orderId;


    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    public Integer getVehicleDeliveryId() {
        return vehicleDeliveryId;
    }

    public void setVehicleDeliveryId(Integer vehicleDeliveryId) {
        this.vehicleDeliveryId = vehicleDeliveryId;
    }

    public LocalDateTime getExpectedDeliveryDate() {
        return ExpectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
        ExpectedDeliveryDate = expectedDeliveryDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getOderId() {
        return orderId;
    }

    public void setOderId(Order orderId) {
        this.orderId = orderId;

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    

    // public LocalDateTime getNgayTaoDon() {
    //     return ngayTaoDon;
    // }

    // public void setNgayTaoDon(LocalDateTime ngayTaoDon) {
    //     this.ngayTaoDon = ngayTaoDon;
    // }

}

