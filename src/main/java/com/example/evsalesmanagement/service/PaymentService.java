package com.example.evsalesmanagement.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.payment.PaymentCreateSummaryDTO;
import com.example.evsalesmanagement.dto.payment.PaymentResponseDTO;
import com.example.evsalesmanagement.dto.payment.PaymentUpdateSummaryDTO;
import com.example.evsalesmanagement.dto.payment.VNPAYResponseDTO;
import com.example.evsalesmanagement.enums.PaymentMethodEnum;
import com.example.evsalesmanagement.enums.PaymentStatusEnum;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.Payment;
import com.example.evsalesmanagement.repository.OrderRepository;
import com.example.evsalesmanagement.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    // Tỷ lệ phạt mỗi ngày: 20%
    private static final BigDecimal PENALTY_RATE_PER_DAY = new BigDecimal("0.20");

    // Lấy tất cả thanh toán
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    // Lấy thanh toán theo trạng thái
    public List<PaymentResponseDTO> getPaymentsByStatus(PaymentStatusEnum status) {
        return paymentRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    
    // Lấy chi tiết thanh toán
    public PaymentResponseDTO getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found payment with ID: " + id));
        if (payment.getStatus() == PaymentStatusEnum.UNPAID) {
            calculatePenalty(payment);
        }
        
        return convertToDTO(payment);
    }


    // Lấy thanh toán theo Order
    public List<PaymentResponseDTO> getPaymentsByOrderId(Integer orderId) {
        return paymentRepository.findByOrder_OrderId(orderId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    // Tạo thanh toán mới
    @Transactional
    public PaymentResponseDTO createPayment(PaymentCreateSummaryDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(dto.getAmount());
        payment.setDueDate(dto.getDueDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentForm(dto.getPaymentForm());
        payment.setNumberCycle(dto.getNumberCycle());
        payment.setStatus(PaymentStatusEnum.UNPAID);
        payment.setPenaltyAmount(BigDecimal.ZERO);
        
        Payment saved = paymentRepository.save(payment);
        return convertToDTO(saved);
    }


    // Cập nhật thanh toán
    @Transactional
    public PaymentResponseDTO updatePayment(Integer id, PaymentUpdateSummaryDTO dto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found payment with ID: " + id));
        
        if (dto.getAmount() != null) {
            payment.setAmount(dto.getAmount());
        }
        if (dto.getDueDate() != null) {
            payment.setDueDate(dto.getDueDate());
        }
        if (dto.getPaymentDate() != null) {
            payment.setPaymentDate(dto.getPaymentDate());
        }
        if (dto.getPenaltyAmount() != null) {
            payment.setPenaltyAmount(dto.getPenaltyAmount());
        }
        if (dto.getStatus() != null) {
            payment.setStatus(dto.getStatus());
        }
        if (dto.getPaymentForm() != null) {
            payment.setPaymentForm(dto.getPaymentForm());
        }
        if (dto.getVnpayCode() != null) {
            payment.setVnpayCode(dto.getVnpayCode());
        }
        
        Payment updated = paymentRepository.save(payment);
        return convertToDTO(updated);
    }


    // Xác nhận thanh toán tiền mặt
    @Transactional
    public PaymentResponseDTO confirmCashPayment(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found payment with ID: " + id));
        
        if (payment.getStatus() == PaymentStatusEnum.PAID) {
            throw new RuntimeException("This payment has already been confirmed");
        }
        
        if (payment.getPaymentMethod() != PaymentMethodEnum.CASH) {
            throw new RuntimeException("Only applicable for cash payments");
        }

        calculatePenalty(payment);
        
        payment.setStatus(PaymentStatusEnum.PAID);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentForm("Full Payment");
        
        Payment updated = paymentRepository.save(payment);
        return convertToDTO(updated);
    }

    

    // Tạo link thanh toán VNPAY 
@Transactional
public VNPAYResponseDTO createVNPAYPayment(Integer id) {
    Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found payment with ID: " + id));
    
    if (payment.getStatus() == PaymentStatusEnum.PAID) {
        throw new RuntimeException("This payment has already been completed");
    }
    
    if (payment.getPaymentMethod() != PaymentMethodEnum.VNPAY) {
        throw new RuntimeException("This payment does not use the VNPAY method");
    }
    
    calculatePenalty(payment);
    
    String vnpayCode = "VNPAY_" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    payment.setVnpayCode(vnpayCode);
    paymentRepository.save(payment);
    
    BigDecimal totalAmount = payment.getAmount().add(payment.getPenaltyAmount());
    
    String paymentUrl = String.format(
        "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?vnp_Amount=%d&vnp_TxnRef=%s&vnp_OrderInfo=Payment_%d",
        totalAmount.multiply(BigDecimal.valueOf(100)).longValue(),
        vnpayCode,
        payment.getPaymentId()
    );
    
    return new VNPAYResponseDTO(paymentUrl, vnpayCode, "Create payment link successfully");
}

// Xử lý callback từ VNPAY 
@Transactional
public PaymentResponseDTO handleVNPAYCallback(String vnpayCode, String responseCode) {
    if (vnpayCode == null || vnpayCode.trim().isEmpty()) {
        throw new RuntimeException("Invalid VNPAY code");
    }
    
    if (responseCode == null || responseCode.trim().isEmpty()) {
        throw new RuntimeException("Invalid response code");
    }
    
    Payment payment = paymentRepository.findByVnpayCode(vnpayCode)
            .orElseThrow(() -> new RuntimeException("Not found payment with code: " + vnpayCode));
    
    if (payment.getStatus() == PaymentStatusEnum.PAID) {
        throw new RuntimeException("This payment has already been processed");
    }
    
    if ("00".equals(responseCode)) {
        payment.setStatus(PaymentStatusEnum.PAID);
        payment.setPaymentDate(LocalDateTime.now());
        
        if (payment.getPaymentForm() == null || payment.getPaymentForm().isEmpty()) {
            payment.setPaymentForm("VNPAY Payment");
        }
    } else {
        payment.setStatus(PaymentStatusEnum.UNPAID);
        // System.err.println("VNPAY payment failed with code: " + responseCode);
    }
    
    Payment updated = paymentRepository.save(payment);
    return convertToDTO(updated);
}

// Xóa thanh toán 
@Transactional
public void deletePayment(Integer id) {
    if (id == null || id <= 0) {
        throw new RuntimeException("Invalid payment ID");
    }
    
    Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found payment with ID: " + id));
    
    if (payment.getStatus() == PaymentStatusEnum.PAID) {
        throw new RuntimeException("Cannot delete a payment that has been completed");
    }
    // if (payment.getOrder() != null) {}
    try {
        paymentRepository.delete(payment);
    } catch (Exception e) {
        throw new RuntimeException("Cannot delete payment. Error: " + e.getMessage());
    }
}

    // Tự động cập nhật tiền phạt 
    @Scheduled(cron = "0 0 1 * * *") 
    @Transactional
    public void updateOverduePayments() {
        List<Payment> overduePayments = paymentRepository.findOverduePayments(
                PaymentStatusEnum.UNPAID, 
                LocalDateTime.now()
        );
        
        for (Payment payment : overduePayments) {
            calculatePenalty(payment);
            paymentRepository.save(payment);
        }
        // System.out.println("Updated " + overduePayments.size() + " overdue payments");
    }

    
    private void calculatePenalty(Payment payment) {
        if (payment.getDueDate() == null || payment.getPaymentDate() != null) {
            return;
        }
        
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(payment.getDueDate())) {
            long daysOverdue = ChronoUnit.DAYS.between(payment.getDueDate(), now);
            BigDecimal penalty = payment.getAmount()
                    .multiply(PENALTY_RATE_PER_DAY)
                    .multiply(BigDecimal.valueOf(daysOverdue))
                    .setScale(2, RoundingMode.HALF_UP);
            
            payment.setPenaltyAmount(penalty);
        } else {
            payment.setPenaltyAmount(BigDecimal.ZERO);
        }
    }
    
    private PaymentResponseDTO convertToDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setPaymentMethod(payment.getPaymentMethod());
        // dto.setPaymentForm(payment.getPaymentForm());
        dto.setAmount(payment.getAmount());
        dto.setNumberCycle(payment.getNumberCycle());
        dto.setDueDate(payment.getDueDate());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPenaltyAmount(payment.getPenaltyAmount());
        dto.setStatus(payment.getStatus());
        dto.setVnpayCode(payment.getVnpayCode());
        // dto.setOrderId(payment.getOrder() != null ? payment.getOrder().getOrderId() : null);cl
        return dto;
    }
}