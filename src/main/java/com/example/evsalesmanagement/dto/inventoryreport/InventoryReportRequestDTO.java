package com.example.evsalesmanagement.dto.inventoryreport;

import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.VehicleStatusEnum;

import jakarta.validation.constraints.Positive;

public class InventoryReportRequestDTO {

    @Positive(message = "Agency ID must be greater than 0")
    private Integer agencyId;

    @Positive(message = "Vehicle Type ID must be greater than 0")
    private Integer vehicleTypeId;

    private VehicleStatusEnum status;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    public InventoryReportRequestDTO() {
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public VehicleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(VehicleStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }
}