package com.example.evsalesmanagement.enums;

public enum TestDriveAppointmentStatusEnum {

    SCHEDULED("Đã Hẹn"),

    ARRIVED("Đã Tới"),

    CANCELLED("Đã Hủy");

    private final String displayName;

    TestDriveAppointmentStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
