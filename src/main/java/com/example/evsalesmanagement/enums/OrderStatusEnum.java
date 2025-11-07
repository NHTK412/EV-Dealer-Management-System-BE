package com.example.evsalesmanagement.enums;

public enum OrderStatusEnum {

    PENDING("Chờ thanh toán"),

    PENDING_DELIVERY("Chờ giao hàng"),

    DELIVERED("Đã giao hàng"),

    PAID("Đã thanh toán"),

    INSTALLMENT("Đang trả góp");

    private final String displayName;

    OrderStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
