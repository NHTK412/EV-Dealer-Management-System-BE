package com.example.evsalesmanagement.dto.warehousereleasenote;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.WarehouseReleaseNoteStatusEnum;
import com.example.evsalesmanagement.model.WarehouseReleaseNote;

public class WarehouseReleaseNoteSummaryDTO {
    private Integer warehouseReleaseNoteId;
    private LocalDateTime warehouseReleaseNoteDate;
    private String reason;
    private BigDecimal totalAmount;
    private String note;
    private WarehouseReleaseNoteStatusEnum status;

    public WarehouseReleaseNoteSummaryDTO() {}

    public WarehouseReleaseNoteSummaryDTO(WarehouseReleaseNote entity) {
        this.warehouseReleaseNoteId = entity.getWarehouseReleaseNoteId();
        this.warehouseReleaseNoteDate = entity.getReleaseDate();
        this.totalAmount = entity.getTotalAmount();
        this.note = entity.getNote();
        this.status = entity.getStatus();
    }

    public Integer getWarehouseReleaseNoteId() {
        return warehouseReleaseNoteId;
    }

    public void setWarehouseReleaseNoteId(Integer warehouseReleaseNoteId) {
        this.warehouseReleaseNoteId = warehouseReleaseNoteId;
    }
    
    public LocalDateTime getWarehouseReleaseNoteDate() {
        return warehouseReleaseNoteDate;
    }

    public void setWarehouseReleaseNoteDate(LocalDateTime warehouseReleaseNoteDate) {
        this.warehouseReleaseNoteDate = warehouseReleaseNoteDate;
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
