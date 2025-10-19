package com.example.evsalesmanagement.enums;
/**
 * Enum trạng thái xử lý phản hồi, vì sau khi xử lý xong chỉ có 1 trạng thái là "Hoàn thành"
 */
public enum TrangThaiXuLy {
    HOAN_THANH("Hoàn thành");

    private final String displayName;

    TrangThaiXuLy(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TrangThaiXuLy getDefault() {
        return HOAN_THANH;
    }

    @Override
    public String toString() {
        return displayName;
    }
}