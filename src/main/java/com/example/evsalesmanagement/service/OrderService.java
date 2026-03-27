package com.example.evsalesmanagement.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.dto.order.OrderFromQuoteRequestDTO;
import com.example.evsalesmanagement.dto.order.OrderRequestDTO;
import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.dto.order.OrderSummaryDTO;
import com.example.evsalesmanagement.dto.payment.PaymentRequestDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.enums.PaymentMethodEnum;
import com.example.evsalesmanagement.enums.PaymentStatusEnum;
import com.example.evsalesmanagement.enums.PaymentTypeEnum;
import com.example.evsalesmanagement.enums.PolicyTypeEnum;
import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.enums.VehicleStatusEnum;
import com.example.evsalesmanagement.enums.WarehouseReleaseNoteStatusEnum;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.MonthlySales;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.Payment;
import com.example.evsalesmanagement.model.Policy;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.model.VehicleDelivery;
import com.example.evsalesmanagement.model.WarehouseReleaseNote;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.MonthlySalesRepository;
import com.example.evsalesmanagement.repository.OrderRepository;
import com.example.evsalesmanagement.repository.PolicyRepository;
import com.example.evsalesmanagement.repository.VehicleRepository;
import com.example.evsalesmanagement.repository.WarehouseReleaseNoteRepository;
import com.example.evsalesmanagement.service.order.calculator.DiscountCalculator;
import com.example.evsalesmanagement.service.order.calculator.QuantityDiscountCalculator;
import com.example.evsalesmanagement.service.order.calculator.SalesDiscountCalculator;
import com.example.evsalesmanagement.service.order.factory.AgencyOrderFactory;
import com.example.evsalesmanagement.service.order.factory.CustomerOrderFactory;
import com.example.evsalesmanagement.service.order.strategy.FullPaymentStrategy;
import com.example.evsalesmanagement.service.order.strategy.InstallmentPaymentStrategy;
import com.example.evsalesmanagement.service.order.strategy.PaymentStrategy;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private EmployeeRepository employeeRepository;

        @Autowired
        private WarehouseReleaseNoteRepository warehouseReleaseNoteRepository;

        @Autowired
        private VehicleRepository vehicleRepository;

        @Autowired
        private PolicyRepository policyRepository;

        @Autowired
        private CustomerOrderFactory customerOrderFactory;

        @Autowired
        private AgencyOrderFactory agencyOrderFactory;

        @Autowired
        private FullPaymentStrategy fullPaymentStrategy;

        @Autowired
        private InstallmentPaymentStrategy installmentPaymentStrategy;

        @Autowired
        private QuantityDiscountCalculator quantityDiscountCalculator;

        @Autowired
        private SalesDiscountCalculator salesDiscountCalculator;

        @Transactional
        public List<OrderSummaryDTO> getOrdersByCustomerId(Integer agencyId, Integer customerId, Pageable pageable) {
                Page<Order> orderSumaryPage = orderRepository.findByAgencyIdAndCustomerId(agencyId, customerId,
                                pageable);

                return orderSumaryPage.stream()
                                .map((orderSumary) -> new OrderSummaryDTO(orderSumary))
                                .toList();
        }

        @Transactional
        public OrderResponseDTO getOrderById(Integer orderId) {
                Order order = orderRepository.findByIdFetchAllRelations(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Order not found with ID: " + orderId));
                return new OrderResponseDTO(order);
        }

        @Transactional
        public OrderResponseDTO createOrderFromQuotation(Integer employeeId, OrderFromQuoteRequestDTO request) {
                Order order = customerOrderFactory
                                .withQuoteId(request.getQuoteId())
                                .withEmployeeId(employeeId)
                                .withNotes(request.getNotes())
                                .createOrder();

                orderRepository.save(order);

                PaymentStrategy paymentStrategy = getPaymentStrategy(request.getPaymentType(),
                                request.getPaymentPlanId());

                paymentStrategy.applyPayment(order);

                if (request.getPaymentType() == PaymentTypeEnum.INSTALLMENT) {
                        order.setStatus(OrderStatusEnum.INSTALLMENT);
                }

                order.setContractNumber("HD" + order.getOrderId());

                processWarehouseRelease(order);

                orderRepository.save(order);
                return new OrderResponseDTO(order);
        }

        public List<OrderSummaryDTO> getOrdersByAgencyId(Integer agencyId, Pageable pageable) {
                Page<Order> ordersPage = orderRepository.findByAgencyId(agencyId, pageable);
                return ordersPage.stream()
                                .map(OrderSummaryDTO::new)
                                .toList();
        }

        public List<OrderSummaryDTO> getOrdersByEmployeeId(Integer employeeId, Pageable pageable) {
                Page<Order> ordersPage = orderRepository.findByEmployeeId(employeeId, pageable);
                return ordersPage.stream()
                                .map(OrderSummaryDTO::new)
                                .toList();
        }

        @Transactional
        public OrderResponseDTO cancelOrderById(Integer orderId) {
                Order order = orderRepository.findByIdFetchAllRelations(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Order not found with ID: " + orderId));

                if (order.getStatus() == OrderStatusEnum.PENDING) {
                        order.setStatus(OrderStatusEnum.CANCEL);

                        WarehouseReleaseNote warehouseReleaseNote = warehouseReleaseNoteRepository
                                        .findByOrder_OrderId(orderId)
                                        .orElse(null);
                        if (warehouseReleaseNote != null) {
                                for (Vehicle vehicle : warehouseReleaseNote.getVehicles()) {
                                        vehicle.setStatus(VehicleStatusEnum.IN_STOCK);
                                }
                        }

                        warehouseReleaseNote.setStatus(WarehouseReleaseNoteStatusEnum.CANCELLED);

                        warehouseReleaseNoteRepository.save(warehouseReleaseNote);

                } else {
                        throw new ConflictException("The order cannot be canceled in its current status");
                }

                orderRepository.save(order);

                return new OrderResponseDTO(order);
        }

        @Transactional
        public OrderResponseDTO createOrder(Integer employeeId, OrderRequestDTO orderRequestDTO) {
                Order order = agencyOrderFactory
                                .withAgencyId(orderRequestDTO.getAgencyId())
                                .withEmployeeId(employeeId)
                                .withNotes(orderRequestDTO.getNotes())
                                .withOrderDetails(orderRequestDTO.getOrderDetails())
                                .createOrder();

                orderRepository.save(order);

                Policy policy = policyRepository.findByAgency_AgencyId(orderRequestDTO.getAgencyId()).orElse(null);

                if (policy != null) {
                        DiscountCalculator discountCalculator = getDiscountCalculator(policy.getPolicyType());
                        discountCalculator.calculateDiscount(order, policy);
                }

                order.setContractNumber("HD" + order.getOrderId());

                processWarehouseRelease(order);

                orderRepository.save(order);
                return new OrderResponseDTO(order);
        }

        private PaymentStrategy getPaymentStrategy(PaymentTypeEnum paymentType, Integer paymentPlanId) {
                if (paymentType == PaymentTypeEnum.FULL_PAYMENT) {
                        return fullPaymentStrategy;
                } else if (paymentType == PaymentTypeEnum.INSTALLMENT) {
                        return installmentPaymentStrategy.withPaymentPlanId(paymentPlanId);
                }
                throw new BadRequestException("Invalid payment type");
        }

        private DiscountCalculator getDiscountCalculator(PolicyTypeEnum policyType) {
                if (policyType == PolicyTypeEnum.QUANTITY) {
                        return quantityDiscountCalculator;
                } else {
                        return salesDiscountCalculator;
                }
        }

        private void processWarehouseRelease(Order order) {
                WarehouseReleaseNote warehouseReleaseNote = new WarehouseReleaseNote();
                warehouseReleaseNote.setEmployeeId(order.getEmployee());
                warehouseReleaseNote.setOder(order);
                warehouseReleaseNote.setTotalAmount(order.getTotalAmount());
                warehouseReleaseNote.setReleaseDate(LocalDateTime.now());

                Map<Integer, Integer> vehicleDetailQuantity = order.getOrderDetails()
                                .stream()
                                .collect(Collectors.toMap(
                                                orderDetail -> orderDetail.getVehicleTypeDetail()
                                                                .getVehicleTypeDetailId(),
                                                orderDetail -> orderDetail.getQuantity()));

                for (Entry<Integer, Integer> entry : vehicleDetailQuantity.entrySet()) {
                        Set<Vehicle> vehicles = vehicleRepository.findAvailableVehicles(
                                        VehicleStatusEnum.IN_STOCK,
                                        order.getEmployee().getAgency().getAgencyId(),
                                        entry.getKey());

                        if (vehicles.size() < entry.getValue()) {
                                throw new ConflictException("Insufficient vehicles in stock");
                        }

                        vehicles.stream()
                                        .limit(entry.getValue())
                                        .forEach(vehicle -> vehicle.setStatus(VehicleStatusEnum.SOLD));

                        warehouseReleaseNote.getVehicles().addAll(vehicles);
                }

                warehouseReleaseNoteRepository.save(warehouseReleaseNote);
        }
}