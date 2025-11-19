package com.example.evsalesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.payment.PaymentCreateSummaryDTO;
import com.example.evsalesmanagement.dto.payment.PaymentResponseDTO;
import com.example.evsalesmanagement.dto.payment.PaymentUpdateSummaryDTO;
import com.example.evsalesmanagement.dto.payment.VNPAYResponseDTO;
import com.example.evsalesmanagement.enums.PaymentStatusEnum;
import com.example.evsalesmanagement.service.PaymentService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Lấy tất cả thanh toán
    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentResponseDTO>>> getAllPayments() {
        List<PaymentResponseDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get all payments successfully", payments)
        );
    }

    // Lấy thanh toán theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> getPaymentById(@PathVariable Integer id) {
        PaymentResponseDTO payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get payment successfully", payment)
        );
    }

    // Lấy thanh toán theo trạng thái
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<PaymentResponseDTO>>> getPaymentsByStatus(
            @PathVariable PaymentStatusEnum status) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByStatus(status);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get payments by status successfully", payments)
        );
    }

    // Lấy thanh toán theo Order ID
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ApiResponse<List<PaymentResponseDTO>>> getPaymentsByOrderId(
            @PathVariable Integer orderId) {
        List<PaymentResponseDTO> payments = paymentService.getPaymentsByOrderId(orderId);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get payments by order successfully", payments)
        );
    }

    // Tạo thanh toán mới
    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> createPayment(
            @Valid @RequestBody PaymentCreateSummaryDTO dto) {
        PaymentResponseDTO payment = paymentService.createPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ApiResponse<>(true, "Payment created successfully", payment)
        );
    }

    // Cập nhật thanh toán
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> updatePayment(
            @PathVariable Integer id,
            @Valid @RequestBody PaymentUpdateSummaryDTO dto) {
        PaymentResponseDTO payment = paymentService.updatePayment(id, dto);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Payment updated successfully", payment)
        );
    }

    // Xác nhận thanh toán tiền mặt
    @PostMapping("/{id}/confirm-cash")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> confirmCashPayment(@PathVariable Integer id) {
        PaymentResponseDTO payment = paymentService.confirmCashPayment(id);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Cash payment confirmed successfully", payment)
        );
    }

    // Tạo link thanh toán VNPAY
    @PostMapping("/{id}/vnpay")
    public ResponseEntity<ApiResponse<VNPAYResponseDTO>> createVNPAYPayment(@PathVariable Integer id) {
        VNPAYResponseDTO vnpayResponse = paymentService.createVNPAYPayment(id);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "VNPAY payment link created successfully", vnpayResponse)
        );
    }

    // Xử lý callback từ VNPAY
    @GetMapping("/vnpay-callback")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> handleVNPAYCallback(
            @RequestParam("vnp_TxnRef") String vnpayCode,
            @RequestParam("vnp_ResponseCode") String responseCode) {
        PaymentResponseDTO payment = paymentService.handleVNPAYCallback(vnpayCode, responseCode);
        
        if ("00".equals(responseCode)) {
            return ResponseEntity.ok(
                new ApiResponse<>(true, "Payment completed successfully", payment)
            );
        } else {
            return ResponseEntity.ok(
                new ApiResponse<>(false, "Payment failed", payment)
            );
        }
    }

    // Xóa thanh toán
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayment(@PathVariable Integer id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Payment deleted successfully", null)
        );
    }
}