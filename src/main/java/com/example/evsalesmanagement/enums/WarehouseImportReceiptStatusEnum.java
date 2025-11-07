package com.example.evsalesmanagement.enums;

public enum WarehouseImportReceiptStatusEnum {

    CREATED("Đã tạo"),

    PROCESSING("Đang xử lý"),

    RECEIVED("Đã nhập kho"),

    CANCELLED("Đã hủy"),

    PARTIALLY_RECEIVED("Nhập một phần"),

    PENDING_APPROVAL("Chờ phê duyệt");

    private final String displayName;

    WarehouseImportReceiptStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
