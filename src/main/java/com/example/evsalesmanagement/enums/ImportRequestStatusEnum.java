package com.example.evsalesmanagement.enums;

public enum ImportRequestStatusEnum {

    REQUESTED("Đã Yêu Cầu"),

    APPROVED("Đã Duyệt"),

    REJECTED("Đã Từ Chối");

    private final String displayName;

    ImportRequestStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
