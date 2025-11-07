package com.example.evsalesmanagement.enums;

public enum PaymentStatusEnum {

    PAID("Đã Thanh Toán"),
    UNPAID("Chưa Thanh Toán");

    private final String displayName;

    PaymentStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
