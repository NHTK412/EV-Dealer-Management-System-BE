package com.example.evsalesmanagement.dto.warehousereleasenote;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.WarehouseReleaseNoteStatusEnum;
import com.example.evsalesmanagement.model.WarehouseReleaseNote;

public class WarehouseReleaseNoteSummaryDTO {
    private Integer warehouseReceiptId;
    private LocalDateTime warehouseReceiptDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;
    private WarehouseReleaseNoteStatusEnum status;

    public WarehouseReleaseNoteSummaryDTO() {}

    public WarehouseReleaseNoteSummaryDTO(WarehouseReleaseNote entity) {
        this.warehouseReceiptId = entity.getWarehouseReleaseNoteId();
        this.warehouseReceiptDate = entity.getReleaseDate();
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
    public WarehouseReleaseNoteStatusEnum getStatus() {
        return status;
    }
    public void setStatus(WarehouseReleaseNoteStatusEnum status) {
        this.status = status;
    }
}
