package com.example.evsalesmanagement.enums;

public enum VehicleDeliveryEnum {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String displayName;

    VehicleDeliveryEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static VehicleDeliveryEnum fromStringEnum(String text) {
        for (VehicleDeliveryEnum status : VehicleDeliveryEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + text);
    }

    public static boolean enumIsValid(String text) {
        if (text == null) return false;
        for (VehicleDeliveryEnum status : VehicleDeliveryEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    public boolean canTransitionToNewStatus(VehicleDeliveryEnum newStatus) {
        switch (this) {
            case PENDING:
                return newStatus == IN_PROGRESS;
            case IN_PROGRESS:
                return newStatus == COMPLETED;
            default:
                return false;
        }
    }

    public VehicleDeliveryEnum getNextStatus() {
        return switch (this) {
            case PENDING -> IN_PROGRESS;
            case IN_PROGRESS -> COMPLETED;
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
