package com.example.evsalesmanagement.enums;

public enum TestDriveAppointmentEnum {
    PENDING("Pending"),
    SCHEDULED("Scheduled"),
    COMPLETED("Completed");

    private final String displayName;

    TestDriveAppointmentEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TestDriveAppointmentEnum fromStringEnum(String text) {
        for (TestDriveAppointmentEnum status : TestDriveAppointmentEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + text);
    }

    public static boolean enumIsValid(String text) {
        if (text == null) return false;
        for (TestDriveAppointmentEnum status : TestDriveAppointmentEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    public boolean canTransitionToNewStatus(TestDriveAppointmentEnum newStatus) {
        switch (this) {
            case PENDING:
                return newStatus == SCHEDULED;
            case SCHEDULED:
                return newStatus == COMPLETED;
            default:
                return false;
        }
    }

    public TestDriveAppointmentEnum getNextStatus() {
        return switch (this) {
            case PENDING -> SCHEDULED;
            case SCHEDULED -> COMPLETED;
            default -> null;
        };
    }

    public boolean isFinalStatus() {
        return this == COMPLETED;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
