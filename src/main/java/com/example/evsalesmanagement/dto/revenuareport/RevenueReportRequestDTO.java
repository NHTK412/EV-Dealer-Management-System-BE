package com.example.evsalesmanagement.dto.revenuareport;

import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.OrderStatusEnum;

public class RevenueReportRequestDTO {

    private Integer agencyId;

    private Integer vehicleTypeId;

    private OrderStatusEnum status;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    public RevenueReportRequestDTO() {}

    // Getters and Setters
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }
}
