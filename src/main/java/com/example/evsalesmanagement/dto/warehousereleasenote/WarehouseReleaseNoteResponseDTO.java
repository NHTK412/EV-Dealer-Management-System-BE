package com.example.evsalesmanagement.dto.warehousereleasenote;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
// import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.dto.agency.AgencyResponseDTO;
import com.example.evsalesmanagement.dto.employee.EmployeeResponseDTO;
import com.example.evsalesmanagement.dto.vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.enums.WarehouseReleaseNoteStatusEnum;
import com.example.evsalesmanagement.model.WarehouseReleaseNote;

public class WarehouseReleaseNoteResponseDTO {
    private Integer warehouseReceiptId;
    private LocalDateTime warehouseReceiptDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;
    private WarehouseReleaseNoteStatusEnum status;
    private EmployeeResponseDTO employee;
    private AgencyResponseDTO agency;

    private List<VehicleResponseDTO> vehicles;

    public WarehouseReleaseNoteResponseDTO() {}

    public WarehouseReleaseNoteResponseDTO(WarehouseReleaseNote entity) {
        this.warehouseReceiptId = entity.getWarehouseReleaseNoteId();
        this.warehouseReceiptDate = entity.getReleaseDate();
        this.reason = entity.getReason();
        this.totalAmount = entity.getTotalAmount();
        this.note = entity.getNote();
        this.status = entity.getStatus();
        if (entity.getEmployeeId() != null) {
            this.employee = new EmployeeResponseDTO(entity.getEmployeeId());
        }
        if (entity.getAgencyId() != null) {
            this.agency = new AgencyResponseDTO(entity.getAgencyId());
        }
        if (entity.getVehicles() != null) {
            this.vehicles = entity.getVehicles().stream()
                .map(VehicleResponseDTO::new)
                .collect(java.util.stream.Collectors.toList());
        }
    }

    public Integer getWarehouseReceiptId() {
        return warehouseReceiptId;
    }

    public void setWarehouseReceiptId(Integer warehouseReceiptId) {
        this.warehouseReceiptId = warehouseReceiptId;
    }

    public LocalDateTime getWarehouseReceiptDate() {
        return warehouseReceiptDate;
    }

    public void setWarehouseReceiptDate(LocalDateTime warehouseReceiptDate) {
        this.warehouseReceiptDate = warehouseReceiptDate;
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

    public WarehouseReleaseNoteStatusEnum getStatus() {
        return status;
    }

    public void setStatus(WarehouseReleaseNoteStatusEnum status) {
        this.status = status;
    }

    public EmployeeResponseDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeResponseDTO employee) {
        this.employee = employee;
    }

    public AgencyResponseDTO getAgency() {
        return agency;
    }

    public void setAgency(AgencyResponseDTO agency) {
        this.agency = agency;
    }

    public List<VehicleResponseDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleResponseDTO> vehicles) {
        this.vehicles = vehicles;
    }

}