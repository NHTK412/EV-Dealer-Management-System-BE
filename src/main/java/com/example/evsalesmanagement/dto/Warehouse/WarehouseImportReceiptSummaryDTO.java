package com.example.evsalesmanagement.dto.warehouse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WarehouseImportReceiptSummaryDTO {
    private Integer warehouseReceiptId;
    private LocalDateTime warehouseReceiptDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;
    private String status;

    public WarehouseImportReceiptSummaryDTO() {}

    public WarehouseImportReceiptSummaryDTO(com.example.evsalesmanagement.model.WarehouseReceipt entity) {
        this.warehouseReceiptId = entity.getWarehouseReceiptId();
        this.warehouseReceiptDate = entity.getWarehouseReceiptDate();
        this.reason = entity.getReason();
        this.totalAmount = entity.getTotalAmount();
        this.note = entity.getNote();
        this.status = entity.getStatus();
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
