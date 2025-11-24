package com.example.evsalesmanagement.controller;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.dto.payment.PaymentCreateSummaryDTO;
import com.example.evsalesmanagement.dto.payment.PaymentRequestDTO;
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

        @GetMapping("/customer/{customerPhone}")
        public ResponseEntity<ApiResponse<List<PaymentResponseDTO>>> getPaymentsByCustomerPhone(
                        @PathVariable String customerPhone, @RequestParam int page, @RequestParam int size) {

                Pageable pageable = PageRequest.of(page - 1, size);

                List<PaymentResponseDTO> payments = paymentService.getPaymentsByCustomerPhone(customerPhone, pageable);

                return ResponseEntity.ok(new ApiResponse<List<PaymentResponseDTO>>(true, null, payments));
        }

        @PatchMapping("/payment/{paymentId}")
        public ResponseEntity<ApiResponse<PaymentResponseDTO>> updatePaymentStatus(@PathVariable Integer paymentId,
                        @RequestBody PaymentRequestDTO paymentRequestDTO) {

                PaymentResponseDTO updatedPayment = paymentService.updatePaymentStatus(paymentId,
                                paymentRequestDTO);

                return ResponseEntity.ok(new ApiResponse<>(true, null, updatedPayment));
        }

}