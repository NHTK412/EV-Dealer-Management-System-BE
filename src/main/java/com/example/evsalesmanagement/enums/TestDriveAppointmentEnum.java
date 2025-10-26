package com.example.evsalesmanagement.enums;

//LichHenLaiThuEnum = TestDriveAppointmentEnum
public enum TestDriveAppointmentEnum {
    WAIT_FOR_CONFIRMATION("Waiting for confirmation"),
    CONFIRMED("Confirmed");

    private final String displayName;

    TestDriveAppointmentEnum(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName; 
    }


    //chuyển trạng thái
    public static TestDriveAppointmentEnum fromStringEnum(String text) {
        for (TestDriveAppointmentEnum status : TestDriveAppointmentEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
            "invalid status " + text);
    }

    // Kiểm tra hợp lệ
    public static boolean enumIsValid(String text){
        if(text == null){
            return false;
        }
        for (TestDriveAppointmentEnum status : TestDriveAppointmentEnum.values()) {
            if (status.displayName.equalsIgnoreCase(text)) {
                return true;
    }
}
        return false;
    }

    // kiểm tra chuyển đổi hợp lệ không
    public boolean canTransitionToNewStatus(TestDriveAppointmentEnum newStatus) {
        switch (this) {
            case WAIT_FOR_CONFIRMATION:
                return newStatus == CONFIRMED;
            case CONFIRMED:
                return false; // Không thể chuyển từ Đã xác nhận sang trạng thái khác
            default:
                return false;
        }
    }

    //Lấy trạng thái tiếp theo
    public TestDriveAppointmentEnum getNextStatus() {
        return switch (this) {
            case WAIT_FOR_CONFIRMATION -> CONFIRMED;
            case CONFIRMED-> null; // Không có trạng thái tiếp theo
        };
    }

    //Kiểm tra trạng thái cuối cùng
    public boolean isFinalStatus() {
        return this == CONFIRMED; 
}
    @Override
    public String toString() {
        return this.displayName;
}
}

    
