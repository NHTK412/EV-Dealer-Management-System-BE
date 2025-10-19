package com.example.evsalesmanagement.enums;

/*
 * Enum định nghĩa trạng thái phản hồi 
 * CHUA_XU_LY -> DANG_XU_LY -> DA_XU_LY
 */

public enum TrangThaiPhanHoi {
    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý");

    private final String displayName;

    TrangThaiPhanHoi(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    /*
     * Chuyển từ String qua Enum
     */
    public static TrangThaiPhanHoi fromStringToEnum(String text) {
        for (TrangThaiPhanHoi status : TrangThaiPhanHoi.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
            "Trạng thái không hợp lệ: " + text);
    }


    /*
     * Kiểm tra xem text có phải enum hợp lệ hay không
     */
    public static boolean enumIsValid(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        
        for (TrangThaiPhanHoi status : TrangThaiPhanHoi.values()) {
            if (status.displayName.equalsIgnoreCase(text.trim())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Kiểm tra chuyển trạng thái hợp lệ hay không
     */
    public boolean canTransitionToNewStatus(TrangThaiPhanHoi newStatus) {
        return switch (this) {
            case CHUA_XU_LY -> newStatus == DANG_XU_LY;
            case DANG_XU_LY -> newStatus == DA_XU_LY;
            case DA_XU_LY -> false;
        };
    }


    /**
     * Lấy trạng thái tiếp theo
     */
  public TrangThaiPhanHoi getNext() {
        return switch (this) {
            case CHUA_XU_LY -> DANG_XU_LY;
            case DANG_XU_LY -> DA_XU_LY;
            case DA_XU_LY -> null;
        };
    }


    /**
     * Trạng thái mặc định khi tạo phản hồi mới
     */
     public static TrangThaiPhanHoi getDefaultStatus() {
        return CHUA_XU_LY;
    }


    /**
     * Kiểm tra xem trạng thái có phải là trạng thái cuối cùng không
     */
    public boolean isFinalStatus() {
        return this == DA_XU_LY;
    }

    @Override 
    public String toString() {
        return this.displayName;
    }
}
