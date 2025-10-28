package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.evsalesmanagement.service.TestDriveAppointmentService;
import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/lichHenLaiThu")
@CrossOrigin(origins = "*")
public class LichHenLaiThuController {

    @Autowired
    private TestDriveAppointmentService lichHenService;

    // Lấy tất cả lịch hẹn lái thử với thông tin khách hàng
    @GetMapping
    public ResponseEntity<ApiResponse<List<Object[]>>> layTatCaLichHen() {
        try {
            List<Object[]> danhSach = lichHenService.layTatCaLichHen();

            if (danhSach.isEmpty()) {
                ApiResponse<List<Object[]>> response = new ApiResponse<>(
                        false,
                        "Không tìm thấy lịch hẹn nào",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    true,
                    "Lấy danh sách lịch hẹn thành công",
                    danhSach);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    false,
                    "Lỗi khi lấy danh sách lịch hẹn: " + e.getMessage(),
                    null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // FIX LỖI :

    // Lấy danh sách lịch hẹn có trạng thái "Chờ xác nhận"
    @GetMapping("/choXacNhan")
    public ResponseEntity<ApiResponse<List<Object[]>>> layLichHenChoXacNhan() {
        try {
            List<Object[]> danhSach = lichHenService.layLichHenChoXacNhan();

            if (danhSach.isEmpty()) {
                ApiResponse<List<Object[]>> response = new ApiResponse<>(
                        false,
                        "Không có lịch hẹn chờ xác nhận",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    true,
                    "Lấy danh sách lịch hẹn chờ xác nhận thành công",
                    danhSach);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    false,
                    "Lỗi khi lấy danh sách: " + e.getMessage(),
                    null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Lấy danh sách lịch hẹn có trạng thái "Đã xác nhận"
    @GetMapping("/daXacNhan")
    public ResponseEntity<ApiResponse<List<Object[]>>> layLichHenDaXacNhan() {
        try {
            List<Object[]> danhSach = lichHenService.layLichHenDaXacNhan();

            if (danhSach.isEmpty()) {
                ApiResponse<List<Object[]>> response = new ApiResponse<>(
                        false,
                        "Không có lịch hẹn đã xác nhận",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    true,
                    "Lấy danh sách lịch hẹn đã xác nhận thành công",
                    danhSach);
            return ResponseEntity.status(HttpStatus.OK).body(response); // 200

        } catch (Exception e) {
            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    false,
                    "Lỗi khi lấy danh sách: " + e.getMessage(),
                    null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // FIX LỖI :
}
