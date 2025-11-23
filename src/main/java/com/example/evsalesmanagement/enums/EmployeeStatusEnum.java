package com.example.evsalesmanagement.enums;

public enum EmployeeStatusEnum {

    ACTIVE("Đang Hoạt Động"),

    INACTIVE("Dừng Hoạt Động");

    private final String displayName;

    EmployeeStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
