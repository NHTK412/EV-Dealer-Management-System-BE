package com.example.evsalesmanagement.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.VehicleDelivery;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.OrderRepository;
import com.example.evsalesmanagement.repository.VehicleDeliveryRepository;

import jakarta.transaction.Transactional;

@Service
public class VehicleDeliveryService {

    @Autowired
    private VehicleDeliveryRepository vehicleDeliveryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public VehicleDeliveryResponseDTO createVehicleDelivery(VehicleDeliveryRequestDTO vehicleDeliveryRequestDTO) {

        Order order = orderRepository.findById(vehicleDeliveryRequestDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id: " + vehicleDeliveryRequestDTO.getOrderId()));

        if (order.getStatus() == OrderStatusEnum.INSTALLMENT || order.getStatus() == OrderStatusEnum.INSTALLMENT) {

            Employee employee = employeeRepository.findById(vehicleDeliveryRequestDTO.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Employee not found with id: " + vehicleDeliveryRequestDTO.getEmployeeId()));

            VehicleDelivery vehicleDelivery = new VehicleDelivery();
            vehicleDelivery.setOrder(order);
            vehicleDelivery.setEmployee(employee);
            vehicleDelivery.setName(vehicleDeliveryRequestDTO.getName());
            vehicleDelivery.setPhoneNumber(vehicleDeliveryRequestDTO.getPhoneNumber());
            vehicleDelivery.setAddress(vehicleDeliveryRequestDTO.getAddress());

            vehicleDelivery.setStatus(VehicleDeliveryStatusEnum.PREPARING);

            VehicleDelivery savedVehicleDelivery = vehicleDeliveryRepository.save(vehicleDelivery);

            return new VehicleDeliveryResponseDTO(savedVehicleDelivery);
        } else {
            throw new ConflictException("Order status is not valid for vehicle delivery.");
        }

    }

    @Transactional
    public VehicleDeliveryResponseDTO updateStatus(Integer vehicleDeliveryId, VehicleDeliveryStatusEnum status) {
        VehicleDelivery vehicleDelivery = vehicleDeliveryRepository.findById(vehicleDeliveryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle Delivery not found with id: " + vehicleDeliveryId));

        if (vehicleDelivery.getStatus() == VehicleDeliveryStatusEnum.DELIVERED) {
            throw new ConflictException("Cannot update status of a delivered vehicle delivery.");
        } else if (status == VehicleDeliveryStatusEnum.DELIVERED) {
            vehicleDelivery.setDeliveryDate(LocalDateTime.now());
        }

        vehicleDelivery.setStatus(status);

        vehicleDeliveryRepository.save(vehicleDelivery);
        return new VehicleDeliveryResponseDTO(vehicleDelivery);

    }

}