package com.example.evsalesmanagement.dto.vehicledelivery;

import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.model.VehicleDelivery;

public class VehicleDeliveryResponseDTO {

    private Integer vehicleDeliveryId;
    private LocalDateTime expectedDeliveryDate;
    private LocalDateTime deliveryDate;
    private VehicleDeliveryStatusEnum status;
    // private Integer orderId;
    private Integer employeeId;

    private String employeeName;

    private String employeePhoneNumber;

    private Integer orderId;

    private String address;

    private String phoneNumber;

    private String name;

    // Default constructor
    public VehicleDeliveryResponseDTO() {
    }

    // Constructor nhận VehicleDelivery
    public VehicleDeliveryResponseDTO(VehicleDelivery vehicleDelivery) {
        this.vehicleDeliveryId = vehicleDelivery.getVehicleDeliveryId();
        // this.expectedDeliveryDate = vehicleDelivery.getExpectedDeliveryDate();
        this.deliveryDate = vehicleDelivery.getDeliveryDate();
        this.status = vehicleDelivery.getStatus();

        this.employeeId = vehicleDelivery.getEmployee().getEmployeeId();
        this.employeeName = vehicleDelivery.getEmployee().getEmployeeName();
        this.employeePhoneNumber = vehicleDelivery.getEmployee().getPhoneNumber();

        this.address = vehicleDelivery.getAddress();
        this.phoneNumber = vehicleDelivery.getPhoneNumber();

        this.name = vehicleDelivery.getName();

        this.orderId = vehicleDelivery.getOrder() != null ? vehicleDelivery.getOrder().getOrderId() : null;

        // Sử dụng getter đúng tên trong model
        // this.orderId = vehicleDelivery.getOderId() != null ?
        // vehicleDelivery.getOderId().getOrderId() : null;
        // this.employeeId = vehicleDelivery.getEmployee() != null ?
        // vehicleDelivery.getEmployee().getEmployeeId() : null;
    }

    // Getters và Setters
    public Integer getVehicleDeliveryId() {
        return vehicleDeliveryId;
    }

    public void setVehicleDeliveryId(Integer vehicleDeliveryId) {
        this.vehicleDeliveryId = vehicleDeliveryId;
    }

    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    // public Integer getOrderId() {
    // return orderId;
    // }

    // public void setOrderId(Integer orderId) {
    // this.orderId = orderId;
    // }

    // public Integer getEmployeeId() {
    // return employeeId;
    // }

    // public void setEmployeeId(Integer employeeId) {
    // this.employeeId = employeeId;
    // }
}
