package com.example.evsalesmanagement.enums;

public enum OrderStatusEnum {

    PENDING("Chờ xử lý"),

    // PENDING_DELIVERY("Chờ giao hàng"),

    // DELIVERED("Đã giao hàng"),

    PAID("Đã thanh toán"),

    INSTALLMENT("Đang trả góp"),

    CANCEL("Hủy đơn hàng");

    private final String displayName;

    OrderStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
