package com.example.evsalesmanagement.enums;

public enum PolicyStatusEnum {

    NOT_ACTIVE("Chưa Hoạt Động"),

    ACTIVE("Đang Hoạt Động"),

    INACTIVE("Dừng Hoạt Động");

    private final String displayName;

    PolicyStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
