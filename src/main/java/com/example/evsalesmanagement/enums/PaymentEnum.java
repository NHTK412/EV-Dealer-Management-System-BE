package com.example.evsalesmanagement.enums;

public enum PaymentEnum {
    PENDING("Pending"),
    PAID("Paid");

    private final String displayName;

    PaymentEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Chuyển từ String sang Enum
    public static PaymentEnum fromStringEnum(String text) {
        for (PaymentEnum status : PaymentEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + text);
    }

    // Kiểm tra String có phải enum hợp lệ không
    public static boolean enumIsValid(String text) {
        if (text == null) return false;
        for (PaymentEnum status : PaymentEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra chuyển trạng thái hợp lệ
    public boolean canTransitionToNewStatus(PaymentEnum newStatus) {
        switch (this) {
            case PENDING:
                return newStatus == PAID;
            default:
                return false;
        }
    }

    // Lấy trạng thái tiếp theo
    public PaymentEnum getNextStatus() {
        return switch (this) {
            case PENDING -> PAID;
            default -> null;
        };
    }

    // Kiểm tra có phải trạng thái cuối
    public boolean isFinalStatus() {
        return this == PAID;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
