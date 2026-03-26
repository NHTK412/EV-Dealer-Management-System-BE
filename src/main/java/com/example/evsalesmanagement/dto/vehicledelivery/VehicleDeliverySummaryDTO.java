package com.example.evsalesmanagement.dto.vehicledelivery;

import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.model.VehicleDelivery;

public class VehicleDeliverySummaryDTO {

    private Integer vehicleDeliveryId;
    private LocalDateTime deliveryDate;
    private VehicleDeliveryStatusEnum status;
    private String name;
    private String phoneNumber;
    private String address;
    private Integer orderId;
    private String employeeName;

    public VehicleDeliverySummaryDTO() {
    }

    public VehicleDeliverySummaryDTO(VehicleDelivery vehicleDelivery) {
        this.vehicleDeliveryId = vehicleDelivery.getVehicleDeliveryId();
        this.deliveryDate = vehicleDelivery.getDeliveryDate();
        this.status = vehicleDelivery.getStatus();
        this.name = vehicleDelivery.getName();
        this.phoneNumber = vehicleDelivery.getPhoneNumber();
        this.address = vehicleDelivery.getAddress();
        this.orderId = vehicleDelivery.getOrder() != null ? vehicleDelivery.getOrder().getOrderId() : null;
        this.employeeName = vehicleDelivery.getEmployee() != null ? vehicleDelivery.getEmployee().getEmployeeName()
                : null;
    }

    public Integer getVehicleDeliveryId() {
        return vehicleDeliveryId;
    }

    public void setVehicleDeliveryId(Integer vehicleDeliveryId) {
        this.vehicleDeliveryId = vehicleDeliveryId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
