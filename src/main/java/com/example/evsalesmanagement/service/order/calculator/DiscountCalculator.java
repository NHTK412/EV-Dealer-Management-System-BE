package com.example.evsalesmanagement.service.order.calculator;


import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.Policy;

public interface DiscountCalculator {
    void calculateDiscount(Order order, Policy policy);
}