package com.example.evsalesmanagement.service.order.strategy;

import com.example.evsalesmanagement.model.Order;

public interface PaymentStrategy {
    void applyPayment(Order order);
}