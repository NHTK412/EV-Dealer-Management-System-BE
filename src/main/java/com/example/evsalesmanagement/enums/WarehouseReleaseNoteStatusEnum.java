package com.example.evsalesmanagement.enums;

public enum WarehouseReleaseNoteStatusEnum {

    CREATED("Đã Tạo"),

    PENDING_APPROVAL("Chờ Duyệt"),

    APPROVED("Đã Duyệt"),

    RELEASED("Đang Giao"),

    DISPATCHED("Đã Giao"),

    CANCELLED("Đã Hủy");

    private final String displayName;

    WarehouseReleaseNoteStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
