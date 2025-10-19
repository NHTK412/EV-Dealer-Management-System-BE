package com.example.evsalesmanagement.utils;

/**
 * Tạm thời để file tiện ích format message: kiểu sẽ là final class chứa các hằng số String dễ thay đổi, dễ fix, dễ quản lý
 */
public class MessageFormat {
    
    // ==================== SUCCESS MESSAGES ====================
    
    // Phản hồi
    public static final String PHAN_HOI_LIST_SUCCESS = "Lấy danh sách phản hồi thành công";
    public static final String PHAN_HOI_DETAIL_SUCCESS = "Lấy chi tiết phản hồi thành công";
    public static final String PHAN_HOI_COUNT_SUCCESS = "Đếm phản hồi thành công";
    
    // Xử lý phản hồi
    public static final String XU_LY_PHAN_HOI_SUCCESS = "Xử lý phản hồi thành công";

    // Thêm các chức năng khác nếu để file util này 
    
    // ==================== ERROR MESSAGES ====================
    
    // Not Found
    public static final String PHAN_HOI_NOT_FOUND = "Không tìm thấy phản hồi với mã: %d";
    public static final String NHAN_VIEN_NOT_FOUND = "Không tìm thấy nhân viên: %d";
    
    // Bad Request
    public static final String TRANG_THAI_INVALID = "Trạng thái không hợp lệ: %s";
    public static final String HINH_THUC_INVALID = "Hình thức giải quyết không hợp lệ: %s";
    public static final String PHAN_HOI_DA_XU_LY = "Phản hồi đã được xử lý";
    public static final String TRANG_THAI_TRANSITION_INVALID = 
        "Không thể chuyển từ '%s' sang '%s'";
    
    // Validation
    public static final String VALIDATION_ERROR = "Dữ liệu không hợp lệ";
    
    // System Error
    public static final String SYSTEM_ERROR = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau.";
    public static final String EMAIL_LINK_ERROR = "Không thể tạo mailto link";
    
    // ==================== CONSTRUCTOR ====================
    
    private MessageFormat() {
        throw new UnsupportedOperationException("Lớp tiện ích không thể khởi tạo");
    }
}