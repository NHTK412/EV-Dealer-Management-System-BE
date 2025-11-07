package com.example.evsalesmanagement.enums;

public enum WarehouseExportReceiptStatusEnum {

    CREATED("Đã Tạo"),

    APPROVED("Đã Duyệt"),

    RELEASED("Đang Giao"),

    DISPATCHED("Đã Giao"),

    CANCELLED("Đã Hủy");

    private final String displayName;

    WarehouseExportReceiptStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
