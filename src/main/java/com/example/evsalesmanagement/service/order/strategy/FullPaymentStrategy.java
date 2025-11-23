package com.example.evsalesmanagement.service.order.strategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.evsalesmanagement.enums.PaymentStatusEnum;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.Payment;

@Component
public class FullPaymentStrategy implements PaymentStrategy {

    @Override
    public void applyPayment(Order order) {
        Payment payment = new Payment();
        payment.setNumberCycle(0);
        payment.setAmount(order.getTotalAmount());
        payment.setDueDate(LocalDateTime.now());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatusEnum.PAID);
        payment.setPenaltyAmount(BigDecimal.ZERO);
        payment.setOrder(order);
        
        order.getPayments().add(payment);
    }
}