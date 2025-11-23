package com.example.evsalesmanagement.model;

import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "VehicleDelivery")
public class VehicleDelivery extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleDeliveryId")
    private Integer vehicleDeliveryId;

    // @Column(name = "NgayTaoDon")
    // private LocalDateTime ngayTaoDon;

    // ngayDuKienGiaoXe = ExpectedDeliveryDate
    // @Column(name = "ExpectedDeliveryDate")
    // private LocalDateTime ExpectedDeliveryDate;

    // ngayGiaoXe = DeliveryDate
    @Column(name = "DeliveryDate")
    private LocalDateTime deliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private VehicleDeliveryStatusEnum status;

    @Column(name = "Address")
    private String address;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "CustomerName")
    private String name;

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

    // public LocalDateTime getExpectedDeliveryDate() {
    //     return ExpectedDeliveryDate;
    // }

    // public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
    //     ExpectedDeliveryDate = expectedDeliveryDate;
    // }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;

    }

    public VehicleDeliveryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(VehicleDeliveryStatusEnum status) {
        this.status = status;
    }

    public Order getOderId() {
        return order;
    }

    public void setOderId(Order order) {
        this.order = order;

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    // public LocalDateTime getNgayTaoDon() {
    // return ngayTaoDon;
    // }

    // public void setNgayTaoDon(LocalDateTime ngayTaoDon) {
    // this.ngayTaoDon = ngayTaoDon;
    // }



}
