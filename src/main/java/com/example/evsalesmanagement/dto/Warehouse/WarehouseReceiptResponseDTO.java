package com.example.evsalesmanagement.dto.Warehouse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.example.evsalesmanagement.dto.AgencyDTO;
import com.example.evsalesmanagement.dto.EmployeeDTO;
import com.example.evsalesmanagement.dto.Vehicle.VehicleResponseDTO;
import com.example.evsalesmanagement.model.WarehouseReceipt;

public class WarehouseReceiptResponseDTO {
    private Integer warehouseReceiptId;
    private LocalDateTime warehouseReceiptDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;
    private EmployeeDTO employee;
    private AgencyDTO agency;
    private List<VehicleResponseDTO> vehicles;

    public WarehouseReceiptResponseDTO() {}

    public WarehouseReceiptResponseDTO(WarehouseReceipt receipt) {
        this.warehouseReceiptId = receipt.getWarehouseReceiptId();
        this.warehouseReceiptDate = receipt.getWareHouseReceiptDate();
    this.reason = receipt.getReason();
        this.totalAmount = receipt.getTotalAmount();
        this.note = receipt.getNote();
        // Map employee, agency, vehicles as needed
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
    public EmployeeDTO getEmployee() {
        return employee;
    }
    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }
    public AgencyDTO getAgency() {
        return agency;
    }
    public void setAgency(AgencyDTO agency) {
        this.agency = agency;
    }
    public List<VehicleResponseDTO> getVehicles() {
        return vehicles;
    }
    public void setVehicles(List<VehicleResponseDTO> vehicles) {
        this.vehicles = vehicles;
    }
}
