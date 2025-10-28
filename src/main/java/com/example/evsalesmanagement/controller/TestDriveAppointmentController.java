package com.example.evsalesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.evsalesmanagement.service.TestDriveAppointmentService;
import com.example.evsalesmanagement.utils.ApiResponse;

@RestController
@RequestMapping("/testDriveAppointment")
@CrossOrigin(origins = "*")
public class TestDriveAppointmentController {

    @Autowired
    private TestDriveAppointmentService testDriveAppointmentService;

    // Lấy tất cả lịch hẹn lái thử với thông tin khách hàng
    @GetMapping
    public ResponseEntity<ApiResponse<List<Object[]>>> getAllTestDriveAppointment() {
        try {
            List<Object[]> objects = testDriveAppointmentService.getAllTestDriveAppointment();

            if (objects.isEmpty()) {
                ApiResponse<List<Object[]>> response = new ApiResponse<>(
                        false,
                        "Không tìm thấy lịch hẹn nào",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    true,
                    "Lấy danh sách lịch hẹn thành công",
                    objects);
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
    @GetMapping("/testDriveAppointmentPendingConfirmation")
    public ResponseEntity<ApiResponse<List<Object[]>>> getTestDriveAppointmentPendingConfirmation() {
        try {
            List<Object[]> objects= testDriveAppointmentService.getTestDriveAppointmentPendingConfirmation();
            if (objects.isEmpty()) {
                ApiResponse<List<Object[]>> response = new ApiResponse<>(
                        false,
                        "Không có lịch hẹn chờ xác nhận",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    true,
                    "Lấy danh sách lịch hẹn chờ xác nhận thành công",
                    objects);
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
    @GetMapping("/testDriveAppointmentConfirmed")
    public ResponseEntity<ApiResponse<List<Object[]>>> getTestDriveAppointmentConfirmed() {
        try {
            List<Object[]> object = testDriveAppointmentService.getTestDriveAppointmentConfirmed();

            if (object.isEmpty()) {
                ApiResponse<List<Object[]>> response = new ApiResponse<>(
                        false,
                        "Không có lịch hẹn đã xác nhận",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ApiResponse<List<Object[]>> response = new ApiResponse<>(
                    true,
                    "Lấy danh sách lịch hẹn đã xác nhận thành công",
                    object);
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
