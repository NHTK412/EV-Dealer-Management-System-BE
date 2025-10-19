package com.example.evsalesmanagement.enums;

public enum LichHenLaiThuEnum {
    CHO_XAC_NHAN("Chờ xác nhận"),
    DA_XAC_NHAN("Đã xác nhận");

    private final String displayName;

    LichHenLaiThuEnum(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName; 
    }


    //chuyển trạng thái
    public static LichHenLaiThuEnum fromStringEnum(String text) {
        for (LichHenLaiThuEnum trangThai : LichHenLaiThuEnum.values()) {
            if (trangThai.displayName.equalsIgnoreCase(text)) {
                return trangThai;
            }
        }
        throw new IllegalArgumentException(
            "Trạng thái không hợp lệ: " + text);
    }

    // Kiểm tra hợp lệ
    public static boolean enumIsValid(String text){
        if(text == null){
            return false;
        }
        for (LichHenLaiThuEnum trangThai : LichHenLaiThuEnum.values()) {
            if (trangThai.displayName.equalsIgnoreCase(text)) {
                return true;
    }
}
        return false;
    }

    // kiểm tra chuyển đổi hợp lệ không
    public boolean canTransitionToTrangThaiMoi(LichHenLaiThuEnum TrangThaiMoi) {
        switch (this) {
            case CHO_XAC_NHAN:
                return TrangThaiMoi == DA_XAC_NHAN;
            case DA_XAC_NHAN:
                return false; // Không thể chuyển từ Đã xác nhận sang trạng thái khác
            default:
                return false;
        }
    }

    //Lấy trạng thái tiếp theo
    public LichHenLaiThuEnum getNextTrangThai() {
        return switch (this) {
            case CHO_XAC_NHAN -> DA_XAC_NHAN;
            case DA_XAC_NHAN -> null; // Không có trạng thái tiếp theo
        };
    }

    //Kiểm tra trạng thái cuối cùng
    public boolean isFinalTrangThai() {
        return this == DA_XAC_NHAN; 
}
    @Override
    public String toString() {
        return this.displayName;
}
}

    
