package com.example.evsalesmanagement.enums;

public enum RevenueReportEnum {
    COMPLETED("Completed"),
    IN_PROCESS("In process"),
    PENDING("Pending");

    private final String label;

    RevenueReportEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static RevenueReportEnum fromString(String value) {
        for (RevenueReportEnum status : RevenueReportEnum.values()) {
            if (status.getLabel().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
            "Status must be one of: " + value +
            ". Valid values are: Completed, In process, Pending"
        );
    }
}
