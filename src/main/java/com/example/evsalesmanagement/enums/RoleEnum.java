package com.example.evsalesmanagement.enums;

public enum RoleEnum {

    ROLE_ADMIN("Quản Trị Viên"),

    ROLE_DEALER_STAFF("Nhân Viên Đại Lý"),

    ROLE_DEALER_MANAGER("Quản Lý Đại Lý"),

    ROLE_EVM_STAFF("Nhân Viên Hãng");

    private final String displayName;

    RoleEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}