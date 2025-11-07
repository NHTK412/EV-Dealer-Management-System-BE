package com.example.evsalesmanagement.enums;

public enum PaymentMethodEnum {

    CASH("Tiền mặt"),

    VNPAY("Chuyển khoản VPAY");

    private final String displayName;

    PaymentMethodEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
