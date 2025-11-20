package com.example.evsalesmanagement.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// <<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// =======
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
// >>>>>>> feat/Khang/cauHinhRedis
import org.springframework.stereotype.Service;

// import com.example.evsalesmanagement.dto.order.OrderFromQuoteRequestDTO;
import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.dto.order.OrderSummaryDTO;
import com.example.evsalesmanagement.dto.payment.PaymentRequestDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.enums.OrderTypeEnum;
import com.example.evsalesmanagement.enums.PaymentMethodEnum;
import com.example.evsalesmanagement.enums.PaymentStatusEnum;
import com.example.evsalesmanagement.enums.PaymentTypeEnum;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.OrderDetail;
import com.example.evsalesmanagement.model.Payment;
import com.example.evsalesmanagement.model.PaymentPlan;
import com.example.evsalesmanagement.model.QuotationDetail;
import com.example.evsalesmanagement.model.Quote;
// import com.example.evsalesmanagement.repository.AgencyRepository;
// import com.example.evsalesmanagement.repository.CustomerRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.OrderRepository;
import com.example.evsalesmanagement.repository.PaymentPlanRepository;
import com.example.evsalesmanagement.repository.QuoteRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private QuoteRepository quoteRepository;

        @Autowired
        private EmployeeRepository employeeRepository;

        // @Autowired
        // private AgencyRepository agencyRepository;

        // @Autowired
        // private CustomerRepository customerRepository;

        @Autowired
        private PaymentPlanRepository paymentPlanRepository;

        @Cacheable(value = "order", key = "#orderId")
        @Transactional
        public OrderResponseDTO getOrderById(Integer orderId) {
                Order order = orderRepository.findByIdFetchAllRelations(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợp lệ"));

                return new OrderResponseDTO(order);
        }

        @Transactional
        // public OrderResponseDTO createOrderFromQuotation(OrderFromQuoteRequestDTO
        // orderFromQuoteRequestDTO) {
        // public OrderResponseDTO createOrderFromQuotation(Integer employeeId,
        // OrderFromQuoteRequestDTO orderFromQuoteRequestDTO) {
        public OrderResponseDTO createOrderFromQuotation(Integer employeeId, Integer quoteId, String note) {

                Quote quote = quoteRepository.findById(quoteId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không hợp lệ"));

                Employee employee = employeeRepository.findById(employeeId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không hợp lệ"));

                // Employee employee =
                // employeeRepository.findById(orderFromQuoteRequestDTO.getEmployeeId())
                // .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không hợp
                // lệ"));

                // Agency agency =
                // agencyRepository.findById(orderFromQuoteRequestDTO.getAgencyId())
                // .orElseThrow(() -> new ResourceNotFoundException("Mã đại lý không hợp lệ"));

                // Customer customer = customerRepository.findById(quote.getEmployee().ge)
                // .orElseThrow(() -> new ResourceNotFoundException("Mã khách hàng không hợp
                // lệ"));

                Customer customer = quote.getCustomer();

                Order order = new Order();

                order.setNotes(note);

                // order.setStatus("CREATE");
                order.setStatus(OrderStatusEnum.PENDING);

                order.setType(OrderTypeEnum.RETAIL_CUSTOMER);

                order.setEmployee(employee);

                // order.setAgency(agency);

                order.setCustomer(customer);

                for (QuotationDetail quotationDetail : quote.getQuotationDetails()) {
                        OrderDetail orderDetail = new OrderDetail();

                        orderDetail.setQuantity(quotationDetail.getQuantity());

                        orderDetail.setDiscount(quotationDetail.getDiscount());

                        orderDetail.setRegistrationTax(quotationDetail.getRegistrationTax());

                        orderDetail.setLicensePlateFee(quotationDetail.getLicensePlateFee());

                        orderDetail.setRegistrartionFee(quotationDetail.getRegistrartionFee());

                        orderDetail.setCompulsoryInsurance(quotationDetail.getCompulsoryInsurance());

                        orderDetail.setCompulsoryInsurance(quotationDetail.getCompulsoryInsurance());

                        orderDetail.setRoadMaintenanceMees(quotationDetail.getRoadMaintenanceMees());

                        orderDetail.setVehicleRegistrationServiceFee(
                                        quotationDetail.getVehicleRegistrationServiceFee());

                        orderDetail.setDiscountPercentage(quotationDetail.getDiscountPercentage());

                        orderDetail.setWholesalePrice(quotationDetail.getWholesalePrice());

                        orderDetail.setTotalAmount(quotationDetail.getTotalAmount());

                        orderDetail.setVehicleTypeDetail(quotationDetail.getVehicleTypeDetail());

                        orderDetail.setMaterialInsurance(quotationDetail.getMaterialInsurance());

                        // Gắn quan hệ
                        orderDetail.setOrder(order);

                        order.getOrderDetails().add(orderDetail);

                }

                order.setTotalAmount(quote.getTotalAmount());

                orderRepository.save(order);

                return new OrderResponseDTO(order);
        }

        @CachePut(value = "order", key = "#orderId")
        @Transactional
        public OrderResponseDTO updateOrderById(Integer orderId, OrderStatusEnum status, String contractNumber) {
                Order order = orderRepository.findByIdFetchAllRelations(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợplệ"));

                if (order.getStatus() != OrderStatusEnum.PAID) {

                        order.setStatus(status != null ? status : order.getStatus());

                        order.setContractNumber(contractNumber != null ? contractNumber : order.getContractNumber());
                } else {
                        throw new ConflictException("Hiện tại không thể cập nhật thông tin");
                }

                orderRepository.save(order);

                return new OrderResponseDTO(order);
        }

        public List<OrderSummaryDTO> getOrdersByAgencyId(Integer agencyId, Pageable pageable) {

                Page<Order> ordersPage = orderRepository.findByAgencyId(agencyId, pageable);

                // List<Order> orders = ordersPage.getContent();

                // List<OrderSummaryDTO> orderSummaryDTOs = orders.stream()
                // .map(order -> new OrderSummaryDTO(order))
                // .toList();

                return ordersPage.stream()
                                .map((order) -> new OrderSummaryDTO(order))
                                .toList();
        }

        public List<OrderSummaryDTO> getOrdersByEmployeeId(Integer employeeId, Pageable pageable) {

                Page<Order> ordersPage = orderRepository.findByEmployeeId(employeeId, pageable);

                // List<Order> orders = ordersPage.getContent();

                // List<OrderSummaryDTO> orderSummaryDTOs = orders.stream()
                // .map(order -> new OrderSummaryDTO(order))
                // .toList();

                return ordersPage.stream()
                                .map((order) -> new OrderSummaryDTO(order))
                                .toList();
        }

        @Transactional
        public OrderResponseDTO createPayment(Integer orderId, PaymentRequestDTO paymentRequestDTO) {

                Order order = orderRepository.findById(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

                if (paymentRequestDTO.getPaymentType() == PaymentTypeEnum.FULL_PAYMENT) {
                        Payment payment = new Payment();

                        payment.setNumberCycle(0);

                        payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());

                        payment.setAmount(order.getTotalAmount());

                        payment.setDueDate(LocalDateTime.now());

                        payment.setPaymentDate(LocalDateTime.now());

                        payment.setStatus(PaymentStatusEnum.PAID);

                        payment.setPenaltyAmount(BigDecimal.valueOf(0));

                        payment.setOrder(order);

                        if (paymentRequestDTO.getPaymentMethod() == PaymentMethodEnum.VNPAY) {
                                payment.setVnpayCode(paymentRequestDTO.getVnpayCode());
                        }

                        order.getPayments().add(payment);

                } else if (paymentRequestDTO.getPaymentType() == PaymentTypeEnum.INSTALLMENT) {
                        PaymentPlan paymentPlan = paymentPlanRepository.findById(paymentRequestDTO.getPaymentPlanId())
                                        .orElseThrow(() -> new ResourceNotFoundException("PaymentPlan Not Found"));

                        // Tổng tiền của đơn hàng (tổng giá trị sản phẩm)
                        BigDecimal total = order.getTotalAmount();

                        // Lãi suất trả góp theo tháng (%) ví dụ: 1.2 nghĩa là 1.2%/tháng
                        BigDecimal interestRate = paymentPlan.getInterestRate();

                        // % trả trước, ví dụ 30 nghĩa là khách trả trước 30%
                        BigDecimal downPercent = paymentPlan.getDownPaymentPercent();

                        // Số kỳ trả góp, ví dụ 6 kỳ = 6 tháng
                        int n = paymentPlan.getNumberOfInstallments();

                        // === 1. TÍNH SỐ TIỀN TRẢ TRƯỚC ===
                        // DownPayment = Total × (downPercent / 100)
                        BigDecimal downPayment = total.multiply(downPercent).divide(BigDecimal.valueOf(100));

                        Payment payment = new Payment();

                        payment.setNumberCycle(0);

                        payment.setAmount(downPayment);

                        payment.setDueDate(LocalDateTime.now());

                        payment.setPaymentDate(LocalDateTime.now());

                        payment.setStatus(PaymentStatusEnum.PAID);

                        payment.setPenaltyAmount(BigDecimal.valueOf(0));

                        payment.setOrder(order);

                        // === 2. SỐ TIỀN PHẢI TRẢ GÓP ===
                        // LoanAmount = Total − DownPayment
                        BigDecimal loanAmount = total.subtract(downPayment);

                        // === 3. CHUYỂN LÃI SUẤT TỪ % SANG SỐ THẬP PHÂN ===
                        // Ví dụ interestRate = 1.2 → 0.012
                        BigDecimal r = interestRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

                        // === 4. TÍNH TIỀN PHẢI TRẢ MỖI KỲ THEO CÔNG THỨC PMT ===
                        // Công thức: LoanAmount × r × (1 + r)^n / ((1 + r)^n − 1)

                        // (1 + r)
                        BigDecimal onePlusR = r.add(BigDecimal.ONE);

                        // (1 + r)^n
                        BigDecimal pow = onePlusR.pow(n);

                        // Tử số: loanAmount × r × (1+r)^n
                        BigDecimal numerator = loanAmount.multiply(r).multiply(pow);

                        // Mẫu số: (1+r)^n − 1
                        BigDecimal denominator = pow.subtract(BigDecimal.ONE);

                        // Tiền mỗi kỳ cần trả (gốc + lãi)
                        BigDecimal installmentAmount = numerator.divide(denominator, 2, RoundingMode.HALF_UP);

                        for (int i = 1; i <= n; i++) {
                                payment = new Payment();

                                payment.setNumberCycle(i);

                                payment.setAmount(installmentAmount);

                                payment.setPaymentDate(LocalDateTime.now().plusMonths(i));

                                payment.setStatus(PaymentStatusEnum.UNPAID);

                                payment.setPenaltyAmount(BigDecimal.valueOf(0));

                                payment.setOrder(order);

                                order.getPayments().add(payment);
                        }

                }

                orderRepository.save(order);

                return new OrderResponseDTO(order);

                // if (paymentType.equals("THANG")) {

                // Payment payment = new Payment();

                // }
        }
}
