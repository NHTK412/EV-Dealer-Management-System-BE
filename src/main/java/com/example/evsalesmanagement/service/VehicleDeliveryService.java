package com.example.evsalesmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliverySummaryDTO;
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

        if (order.getStatus() == OrderStatusEnum.INSTALLMENT || order.getStatus() == OrderStatusEnum.PAID) {

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

    // Lấy danh sách đơn giao hàng của đại lý
    public List<VehicleDeliverySummaryDTO> getAllByAgencyId(Integer agencyId, Pageable pageable) {
        Page<VehicleDelivery> deliveries = vehicleDeliveryRepository.findByAgencyId(agencyId, pageable);
        return deliveries.stream()
                .map(VehicleDeliverySummaryDTO::new)
                .collect(Collectors.toList());
    }

    // Lấy chi tiết đơn giao hàng của đại lý
    public VehicleDeliveryResponseDTO getByIdAndAgencyId(Integer vehicleDeliveryId, Integer agencyId) {
        VehicleDelivery vehicleDelivery = vehicleDeliveryRepository
                .findByIdAndAgencyId(vehicleDeliveryId, agencyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy đơn giao hàng với id: " + vehicleDeliveryId + " thuộc đại lý: " + agencyId));
        return new VehicleDeliveryResponseDTO(vehicleDelivery);
    }

    // Lấy chi tiết đơn giao hàng (tất cả quyền)
    public VehicleDeliveryResponseDTO getById(Integer vehicleDeliveryId) {
        VehicleDelivery vehicleDelivery = vehicleDeliveryRepository
                .findByIdWithDetails(vehicleDeliveryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy đơn giao hàng với id: " + vehicleDeliveryId));
        return new VehicleDeliveryResponseDTO(vehicleDelivery);
    }

}