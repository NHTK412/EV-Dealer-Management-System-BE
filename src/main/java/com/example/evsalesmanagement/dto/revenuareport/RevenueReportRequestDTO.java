package com.example.evsalesmanagement.dto.revenuareport;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;


public class RevenueReportRequestDTO {

    @NotNull(message = "agencyId must not be null")
    private Integer agencyId;

    @NotNull(message = "vehicleTypeId must not be null")
    private Integer vehicleTypeId;

    @Pattern(
        regexp = "^(Completed|In process|Pending)?$",
        message = "Status is invalid. Allowed values are: Completed, In process, Pending"
    )
    private String status;

    @NotNull(message = "fromDate must not be null")
    @PastOrPresent(message = "fromDate must be in the past or present")
    private LocalDateTime fromDate;

    @NotNull(message = "toDate must not be null")
    @PastOrPresent(message = "toDate must be in the past or present")
    private LocalDateTime toDate;

    public RevenueReportRequestDTO() {}

    // Getters and Setters
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public void setStatus(String status) {
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }
}
