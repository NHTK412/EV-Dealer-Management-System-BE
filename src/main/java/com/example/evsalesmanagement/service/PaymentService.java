package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.payment.PaymentResponseDTO;
import com.example.evsalesmanagement.model.Payment;
import com.example.evsalesmanagement.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    // Lấy tất cả payment
    public List<PaymentResponseDTO> getAllPayments() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Lấy payment trạng thái Pending
    public List<PaymentResponseDTO> getPendingPayments() {
        return repository.findPendingPayments()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Lấy payment trạng thái Paid
    public List<PaymentResponseDTO> getPaidPayments() {
        return repository.findPaidPayments()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Chuyển từ entity sang DTO
    private PaymentResponseDTO convertToDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentForm(payment.getPaymentForm());
        dto.setAmount(payment.getAmount());
        dto.setNumberCycle(payment.getNumberCycle());
        dto.setDueDate(payment.getDueDate());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPenaltyAmount(payment.getPenaltyAmount());
        dto.setStatus(payment.getStatus());
        dto.setVnpayCode(payment.getVnpayCode());
        dto.setOrderId(payment.getOrder() != null ? payment.getOrder().getOrderId() : null);
        return dto;
    }
}
