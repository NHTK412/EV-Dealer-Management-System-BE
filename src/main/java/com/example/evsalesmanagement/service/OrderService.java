package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderResponseDTO getOrderById(Integer orderId) {
        Order order = orderRepository.findByIdFetchAllRelations(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợplệ"));

        // Order order = orderRepository.findById(orderId)
        // .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợp
        // lệ"));

        // System.err.println("CHECK ---> " + " " + order.getOrderId() + " " +
                // order.getContractNumber());

        return new OrderResponseDTO(order);
    }

}
