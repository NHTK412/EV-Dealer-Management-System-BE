package com.example.evsalesmanagement.dto.inventoryreport;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class InventoryReportRequestDTO {

    @Min(value = 1, message = "Agency ID must be greater than 0")
    private Integer agencyId;

    @Min(value = 1, message = "Vehicle Type ID must be greater than 0")
    private Integer vehicleTypeId;

    @Pattern(regexp = "^(AVAILABLE|SOLD|RESERVED)?$", message = "Status must be one of the following values: AVAILABLE, SOLD, RESERVED")
    private String status;

    @NotNull(message = "From date must not be null")
    private LocalDateTime fromDate;

    @NotNull(message = "To date must not be null")
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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