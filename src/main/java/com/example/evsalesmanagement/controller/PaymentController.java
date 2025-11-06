package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.payment.PaymentResponseDTO;
import com.example.evsalesmanagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Lấy tất cả payment
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        List<PaymentResponseDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // Lấy payment trạng thái Pending
    @GetMapping("/pending")
    public ResponseEntity<List<PaymentResponseDTO>> getPendingPayments() {
        List<PaymentResponseDTO> pendingPayments = paymentService.getPendingPayments();
        return ResponseEntity.ok(pendingPayments);
    }

    // Lấy payment trạng thái Paid
    @GetMapping("/paid")
    public ResponseEntity<List<PaymentResponseDTO>> getPaidPayments() {
        List<PaymentResponseDTO> paidPayments = paymentService.getPaidPayments();
        return ResponseEntity.ok(paidPayments);
    }
}
