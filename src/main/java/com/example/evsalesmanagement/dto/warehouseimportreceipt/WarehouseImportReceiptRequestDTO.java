package com.example.evsalesmanagement.dto.warehouseimportreceipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class WarehouseImportReceiptRequestDTO {
    private LocalDateTime warehouseReceiptDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;
    private String status;
    private Integer employeeId;
    private Integer agencyId;
    private List<Integer> vehicleIds;

    public WarehouseImportReceiptRequestDTO() {}

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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public List<Integer> getVehicleIds() {
        return vehicleIds;
    }
    public void setVehicleIds(List<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
