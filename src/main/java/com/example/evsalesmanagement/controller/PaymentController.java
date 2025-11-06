package com.example.evsalesmanagement.controller;

import com.example.evsalesmanagement.dto.PaymentDTO;
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
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // Lấy payment trạng thái Pending
    @GetMapping("/pending")
    public ResponseEntity<List<PaymentDTO>> getPendingPayments() {
        List<PaymentDTO> pendingPayments = paymentService.getPendingPayments();
        return ResponseEntity.ok(pendingPayments);
    }

    // Lấy payment trạng thái Paid
    @GetMapping("/paid")
    public ResponseEntity<List<PaymentDTO>> getPaidPayments() {
        List<PaymentDTO> paidPayments = paymentService.getPaidPayments();
        return ResponseEntity.ok(paidPayments);
    }
}
