package com.example.evsalesmanagement.enums;

public enum EmployeePositionEnum {
    ADMIN("Quản Trị Viên"),

    DEALER_STAFF("Nhân Viên Đại Lý"),

    DEALER_MANAGER("Quản Lý Đại Lý"),

    EVM_STAFF("Nhân Viên Hãng");

    private final String displayName;

    EmployeePositionEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
