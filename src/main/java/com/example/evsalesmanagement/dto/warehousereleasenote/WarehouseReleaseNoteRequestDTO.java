package com.example.evsalesmanagement.dto.warehousereleasenote;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import com.example.evsalesmanagement.enums.WarehouseReleaseNoteStatusEnum;

public class WarehouseReleaseNoteRequestDTO {
    private LocalDateTime releaseDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;
    private WarehouseReleaseNoteStatusEnum status;
    private Integer employeeId;
    private Integer agencyId;
    private Set<Integer> vehicleIds;

    public WarehouseReleaseNoteRequestDTO() {}

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    
    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public Integer getAgencyId() {
        return agencyId;
    }
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
    public Set<Integer> getVehicleIds() {
        return vehicleIds;
    }
    public void setVehicleIds(Set<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public WarehouseReleaseNoteStatusEnum getStatus() {
        return status;
    }

    public void setStatus(WarehouseReleaseNoteStatusEnum status) {
        this.status = status;
    }

}
