package com.example.evsalesmanagement.dto;

import java.time.LocalDateTime;
import com.example.evsalesmanagement.model.VehicleDelivery;

public class VehicleDeliveryDTO {

    private Integer vehicleDeliveryId;
    private LocalDateTime expectedDeliveryDate;
    private LocalDateTime deliveryDate;
    private String status;
    private Integer orderId;
    private Integer employeeId;

    // Default constructor
    public VehicleDeliveryDTO() {}

    // Constructor nhận VehicleDelivery
    public VehicleDeliveryDTO(VehicleDelivery vehicleDelivery) {
        this.vehicleDeliveryId = vehicleDelivery.getVehicleDeliveryId();
        this.expectedDeliveryDate = vehicleDelivery.getExpectedDeliveryDate();
        this.deliveryDate = vehicleDelivery.getDeliveryDate();
        this.status = vehicleDelivery.getStatus();
        // Sử dụng getter đúng tên trong model
        this.orderId = vehicleDelivery.getOderId() != null ? vehicleDelivery.getOderId().getOrderId() : null;
        this.employeeId = vehicleDelivery.getEmployee() != null ? vehicleDelivery.getEmployee().getEmployeeId() : null;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
