// package com.example.evsalesmanagement.service;

// import java.math.BigDecimal;
// import java.math.RoundingMode;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Map;
// import java.util.Map.Entry;
// import java.util.Set;
// // import java.util.stream.Collector;
// import java.util.stream.Collectors;

// import org.docx4j.model.datastorage.XPathEnhancerParser.primaryExpr_return;
// import org.springframework.beans.factory.annotation.Autowired;
// // <<<<<<< HEAD
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// // =======
// import org.springframework.cache.annotation.CachePut;
// import org.springframework.cache.annotation.Cacheable;
// // >>>>>>> feat/Khang/cauHinhRedis
// import org.springframework.stereotype.Service;

// import com.example.evsalesmanagement.dto.order.OrderFromQuoteRequestDTO;
// import com.example.evsalesmanagement.dto.order.OrderRequestDTO;
// // import com.example.evsalesmanagement.dto.order.OrderFromQuoteRequestDTO;
// import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
// import com.example.evsalesmanagement.dto.order.OrderSummaryDTO;
// import com.example.evsalesmanagement.dto.payment.PaymentRequestDTO;
// // import com.example.evsalesmanagement.dto.payment.PaymentRequestDTO;
// import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
// import com.example.evsalesmanagement.enums.OrderStatusEnum;
// import com.example.evsalesmanagement.enums.OrderTypeEnum;
// import com.example.evsalesmanagement.enums.PaymentMethodEnum;
// import com.example.evsalesmanagement.enums.PaymentStatusEnum;
// import com.example.evsalesmanagement.enums.PaymentTypeEnum;
// import com.example.evsalesmanagement.enums.PolicyTypeEnum;
// import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
// import com.example.evsalesmanagement.enums.VehicleStatusEnum;
// import com.example.evsalesmanagement.exception.ConflictException;
// import com.example.evsalesmanagement.exception.ResourceNotFoundException;
// import com.example.evsalesmanagement.model.Agency;
// import com.example.evsalesmanagement.model.Customer;
// import com.example.evsalesmanagement.model.Employee;
// import com.example.evsalesmanagement.model.MonthlySales;
// import com.example.evsalesmanagement.model.Order;
// import com.example.evsalesmanagement.model.OrderDetail;
// import com.example.evsalesmanagement.model.Payment;
// import com.example.evsalesmanagement.model.PaymentPlan;
// import com.example.evsalesmanagement.model.Policy;
// import com.example.evsalesmanagement.model.QuantityDiscountLevel;
// import com.example.evsalesmanagement.model.QuotationDetail;
// import com.example.evsalesmanagement.model.Quote;
// import com.example.evsalesmanagement.model.Vehicle;
// import com.example.evsalesmanagement.model.VehicleDelivery;
// import com.example.evsalesmanagement.model.VehicleTypeDetail;
// import com.example.evsalesmanagement.model.WarehouseReleaseNote;
// import com.example.evsalesmanagement.repository.AgencyRepository;
// import com.example.evsalesmanagement.repository.AgencyWholesalePriceRepository;
// // import com.example.evsalesmanagement.repository.AgencyRepository;
// // import com.example.evsalesmanagement.repository.CustomerRepository;
// import com.example.evsalesmanagement.repository.EmployeeRepository;
// import com.example.evsalesmanagement.repository.MonthlySalesRepository;
// import com.example.evsalesmanagement.repository.OrderRepository;
// import com.example.evsalesmanagement.repository.PaymentPlanRepository;
// import com.example.evsalesmanagement.repository.PolicyRepository;
// import com.example.evsalesmanagement.repository.QuoteRepository;
// // import com.example.evsalesmanagement.repository.VehicleDeliveryRepository;
// import com.example.evsalesmanagement.repository.VehicleRepository;
// import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;
// import com.example.evsalesmanagement.repository.WarehouseReleaseNoteRepository;

// import jakarta.transaction.Transactional;

// @Service
// public class OrderService {

//         @Autowired
//         private OrderRepository orderRepository;

//         @Autowired
//         private QuoteRepository quoteRepository;

//         @Autowired
//         private EmployeeRepository employeeRepository;

//         // @Autowired
//         // private VehicleDeliveryRepository vehicleDeliveryRepository;

//         @Autowired
//         private WarehouseReleaseNoteRepository warehouseReleaseNoteRepository;

//         @Autowired
//         private VehicleRepository vehicleRepository;

//         @Autowired
//         private AgencyRepository agencyRepository;

//         @Autowired
//         private AgencyWholesalePriceRepository agencyWholesalePriceRepository;

//         @Autowired
//         private VehicleTypeDetailRepository vehicleTypeDetailRepository;

//         @Autowired
//         private PolicyRepository policyRepository;

//         @Autowired
//         private MonthlySalesRepository monthlySalesRepository;

//         // @Autowired
//         // private AgencyRepository agencyRepository;

//         // @Autowired
//         // private CustomerRepository customerRepository;

//         @Autowired
//         private PaymentPlanRepository paymentPlanRepository;

//         // @Cacheable(value = "order", key = "#orderId")
//         @Transactional
//         public OrderResponseDTO getOrderById(Integer orderId) {
//                 Order order = orderRepository.findByIdFetchAllRelations(orderId)
//                                 .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợp lệ"));

//                 return new OrderResponseDTO(order);
//         }

//         @Transactional
//         // public OrderResponseDTO createOrderFromQuotation(OrderFromQuoteRequestDTO
//         // orderFromQuoteRequestDTO) {
//         // public OrderResponseDTO createOrderFromQuotation(Integer employeeId,
//         // OrderFromQuoteRequestDTO orderFromQuoteRequestDTO) {
//         // public OrderResponseDTO createOrderFromQuotation(Integer employeeId, Integer
//         // quoteId, String note) {
//         public OrderResponseDTO createOrderFromQuotation(Integer employeeId,
//                         OrderFromQuoteRequestDTO orderFromQuoteRequestDTO) {

//                 Quote quote = quoteRepository.findById(orderFromQuoteRequestDTO.getQuoteId())
//                                 .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không hợp lệ"));

//                 Employee employee = employeeRepository.findById(employeeId)
//                                 .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không hợp lệ"));

//                 // Employee employee =
//                 // employeeRepository.findById(orderFromQuoteRequestDTO.getEmployeeId())
//                 // .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không hợp
//                 // lệ"));

//                 // Agency agency =
//                 // agencyRepository.findById(orderFromQuoteRequestDTO.getAgencyId())
//                 // .orElseThrow(() -> new ResourceNotFoundException("Mã đại lý không hợp lệ"));

//                 // Customer customer = customerRepository.findById(quote.getEmployee().ge)
//                 // .orElseThrow(() -> new ResourceNotFoundException("Mã khách hàng không hợp
//                 // lệ"));

//                 Customer customer = quote.getCustomer();

//                 Order order = new Order();

//                 order.setAgency(employee.getAgency());

//                 order.setNotes(orderFromQuoteRequestDTO.getNotes());

//                 // order.setStatus("CREATE");
//                 order.setStatus(OrderStatusEnum.PENDING);

//                 order.setType(OrderTypeEnum.CUSTOMER);

//                 order.setEmployee(employee);

//                 // order.setAgency(agency);

//                 order.setCustomer(customer);

//                 for (QuotationDetail quotationDetail : quote.getQuotationDetails()) {
//                         OrderDetail orderDetail = new OrderDetail();

//                         orderDetail.setQuantity(quotationDetail.getQuantity());

//                         orderDetail.setDiscount(quotationDetail.getDiscount());

//                         orderDetail.setRegistrationTax(quotationDetail.getRegistrationTax());

//                         orderDetail.setLicensePlateFee(quotationDetail.getLicensePlateFee());

//                         orderDetail.setRegistrartionFee(quotationDetail.getRegistrartionFee());

//                         orderDetail.setCompulsoryInsurance(quotationDetail.getCompulsoryInsurance());

//                         orderDetail.setCompulsoryInsurance(quotationDetail.getCompulsoryInsurance());

//                         orderDetail.setRoadMaintenanceMees(quotationDetail.getRoadMaintenanceMees());

//                         orderDetail.setVehicleRegistrationServiceFee(
//                                         quotationDetail.getVehicleRegistrationServiceFee());

//                         orderDetail.setDiscountPercentage(quotationDetail.getDiscountPercentage());

//                         orderDetail.setPrice(quotationDetail.getPrice());

//                         orderDetail.setTotalAmount(quotationDetail.getTotalAmount());

//                         orderDetail.setVehicleTypeDetail(quotationDetail.getVehicleTypeDetail());

//                         orderDetail.setMaterialInsurance(quotationDetail.getMaterialInsurance());

//                         // Gắn quan hệ
//                         orderDetail.setOrder(order);

//                         order.getOrderDetails().add(orderDetail);

//                 }

//                 order.setOriginaAmount(quote.getTotalAmount());

//                 order.setTotalAmount(quote.getTotalAmount());

//                 orderRepository.save(order);

//                 if (orderFromQuoteRequestDTO.getPaymentType() == PaymentTypeEnum.FULL_PAYMENT) {
//                         Payment payment = new Payment();

//                         payment.setNumberCycle(0);

//                         // payment.setPaymentMethod(orderFromQuoteRequestDTO.getPaymentMethod());

//                         payment.setAmount(order.getTotalAmount());

//                         payment.setDueDate(LocalDateTime.now());

//                         payment.setPaymentDate(LocalDateTime.now());

//                         payment.setStatus(PaymentStatusEnum.PAID);

//                         payment.setPenaltyAmount(BigDecimal.valueOf(0));

//                         payment.setOrder(order);

//                         // if (paymentRequestDTO.getPaymentMethod() == PaymentMethodEnum.VNPAY) {
//                         // payment.setVnpayCode(paymentRequestDTO.getVnpayCode());
//                         // }

//                         order.getPayments().add(payment);

//                 } else if (orderFromQuoteRequestDTO.getPaymentType() == PaymentTypeEnum.INSTALLMENT) {
//                         PaymentPlan paymentPlan = paymentPlanRepository
//                                         .findById(orderFromQuoteRequestDTO.getPaymentPlanId())
//                                         .orElseThrow(() -> new ResourceNotFoundException("PaymentPlan Not Found"));

//                         // Tổng tiền của đơn hàng (tổng giá trị sản phẩm)
//                         BigDecimal total = order.getTotalAmount();

//                         // Lãi suất trả góp theo tháng (%) ví dụ: 1.2 nghĩa là 1.2%/tháng
//                         BigDecimal interestRate = paymentPlan.getInterestRate();

//                         // % trả trước, ví dụ 30 nghĩa là khách trả trước 30%
//                         BigDecimal downPercent = paymentPlan.getDownPaymentPercent();

//                         // Số kỳ trả góp, ví dụ 6 kỳ = 6 tháng
//                         int n = paymentPlan.getNumberOfInstallments();

//                         // === 1. TÍNH SỐ TIỀN TRẢ TRƯỚC ===
//                         // DownPayment = Total × (downPercent / 100)
//                         BigDecimal downPayment = total.multiply(downPercent).divide(BigDecimal.valueOf(100));

//                         Payment payment = new Payment();

//                         payment.setNumberCycle(0);

//                         payment.setAmount(downPayment);

//                         payment.setDueDate(LocalDateTime.now());

//                         payment.setPaymentDate(LocalDateTime.now());

//                         payment.setStatus(PaymentStatusEnum.PAID);

//                         payment.setPenaltyAmount(BigDecimal.valueOf(0));

//                         payment.setOrder(order);

//                         // === 2. SỐ TIỀN PHẢI TRẢ GÓP ===
//                         // LoanAmount = Total − DownPayment
//                         BigDecimal loanAmount = total.subtract(downPayment);

//                         // === 3. CHUYỂN LÃI SUẤT TỪ % SANG SỐ THẬP PHÂN ===
//                         // Ví dụ interestRate = 1.2 → 0.012
//                         BigDecimal r = interestRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

//                         // === 4. TÍNH TIỀN PHẢI TRẢ MỖI KỲ THEO CÔNG THỨC PMT ===
//                         // Công thức: LoanAmount × r × (1 + r)^n / ((1 + r)^n − 1)

//                         // (1 + r)
//                         BigDecimal onePlusR = r.add(BigDecimal.ONE);

//                         // (1 + r)^n
//                         BigDecimal pow = onePlusR.pow(n);

//                         // Tử số: loanAmount × r × (1+r)^n
//                         BigDecimal numerator = loanAmount.multiply(r).multiply(pow);

//                         // Mẫu số: (1+r)^n − 1
//                         BigDecimal denominator = pow.subtract(BigDecimal.ONE);

//                         // Tiền mỗi kỳ cần trả (gốc + lãi)
//                         BigDecimal installmentAmount = numerator.divide(denominator, 2, RoundingMode.HALF_UP);

//                         for (int i = 1; i <= n; i++) {
//                                 payment = new Payment();

//                                 payment.setNumberCycle(i);

//                                 payment.setAmount(installmentAmount);

//                                 payment.setDueDate(LocalDateTime.now().plusMonths(i));

//                                 payment.setStatus(PaymentStatusEnum.UNPAID);

//                                 payment.setPenaltyAmount(BigDecimal.valueOf(0));

//                                 payment.setOrder(order);

//                                 order.getPayments().add(payment);
//                         }

//                 }
//                 orderRepository.save(order);

//                 return new OrderResponseDTO(order);
//         }

//         @CachePut(value = "order", key = "#orderId")
//         @Transactional
//         public OrderResponseDTO updateOrderById(Integer orderId, OrderStatusEnum status, String contractNumber) {
//                 Order order = orderRepository.findByIdFetchAllRelations(orderId)
//                                 .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợplệ"));

//                 if (order.getStatus() != OrderStatusEnum.PAID) {

//                         order.setStatus(status != null ? status : order.getStatus());

//                         order.setContractNumber(contractNumber != null ? contractNumber : order.getContractNumber());
//                 } else {
//                         throw new ConflictException("Hiện tại không thể cập nhật thông tin");
//                 }

//                 orderRepository.save(order);

//                 return new OrderResponseDTO(order);
//         }

//         public List<OrderSummaryDTO> getOrdersByAgencyId(Integer agencyId, Pageable pageable) {

//                 Page<Order> ordersPage = orderRepository.findByAgencyId(agencyId, pageable);

//                 // List<Order> orders = ordersPage.getContent();

//                 // List<OrderSummaryDTO> orderSummaryDTOs = orders.stream()
//                 // .map(order -> new OrderSummaryDTO(order))
//                 // .toList();

//                 return ordersPage.stream()
//                                 .map((order) -> new OrderSummaryDTO(order))
//                                 .toList();
//         }

//         public List<OrderSummaryDTO> getOrdersByEmployeeId(Integer employeeId, Pageable pageable) {

//                 Page<Order> ordersPage = orderRepository.findByEmployeeId(employeeId, pageable);

//                 // List<Order> orders = ordersPage.getContent();

//                 // List<OrderSummaryDTO> orderSummaryDTOs = orders.stream()
//                 // .map(order -> new OrderSummaryDTO(order))
//                 // .toList();

//                 return ordersPage.stream()
//                                 .map((order) -> new OrderSummaryDTO(order))
//                                 .toList();
//         }

//         // @Transactional
//         // // public OrderResponseDTO createPayment(Integer orderId, PaymentRequestDTO
//         // // paymentRequestDTO) {
//         // public OrderResponseDTO createPayment(Integer orderId, PaymentTypeEnum
//         // paymentTypeEnum, Integer paymentPlanId) {

//         // Order order = orderRepository.findById(orderId)
//         // .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

//         // order.setStatus(OrderStatusEnum.PAID);

//         // if (paymentTypeEnum == PaymentTypeEnum.FULL_PAYMENT) {
//         // Payment payment = new Payment();

//         // payment.setNumberCycle(0);

//         // // payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());

//         // payment.setAmount(order.getTotalAmount());

//         // payment.setDueDate(LocalDateTime.now());

//         // payment.setPaymentDate(LocalDateTime.now());

//         // payment.setStatus(PaymentStatusEnum.PAID);

//         // payment.setPenaltyAmount(BigDecimal.valueOf(0));

//         // payment.setOrder(order);

//         // // if (paymentRequestDTO.getPaymentMethod() == PaymentMethodEnum.VNPAY) {
//         // // payment.setVnpayCode(paymentRequestDTO.getVnpayCode());
//         // // }

//         // order.getPayments().add(payment);

//         // } else if (paymentTypeEnum == PaymentTypeEnum.INSTALLMENT) {
//         // PaymentPlan paymentPlan = paymentPlanRepository.findById(paymentPlanId)
//         // .orElseThrow(() -> new ResourceNotFoundException("PaymentPlan Not Found"));

//         // // Tổng tiền của đơn hàng (tổng giá trị sản phẩm)
//         // BigDecimal total = order.getTotalAmount();

//         // // Lãi suất trả góp theo tháng (%) ví dụ: 1.2 nghĩa là 1.2%/tháng
//         // BigDecimal interestRate = paymentPlan.getInterestRate();

//         // // % trả trước, ví dụ 30 nghĩa là khách trả trước 30%
//         // BigDecimal downPercent = paymentPlan.getDownPaymentPercent();

//         // // Số kỳ trả góp, ví dụ 6 kỳ = 6 tháng
//         // int n = paymentPlan.getNumberOfInstallments();

//         // // === 1. TÍNH SỐ TIỀN TRẢ TRƯỚC ===
//         // // DownPayment = Total × (downPercent / 100)
//         // BigDecimal downPayment =
//         // total.multiply(downPercent).divide(BigDecimal.valueOf(100));

//         // Payment payment = new Payment();

//         // payment.setNumberCycle(0);

//         // payment.setAmount(downPayment);

//         // payment.setDueDate(LocalDateTime.now());

//         // payment.setPaymentDate(LocalDateTime.now());

//         // payment.setStatus(PaymentStatusEnum.PAID);

//         // payment.setPenaltyAmount(BigDecimal.valueOf(0));

//         // payment.setOrder(order);

//         // // === 2. SỐ TIỀN PHẢI TRẢ GÓP ===
//         // // LoanAmount = Total − DownPayment
//         // BigDecimal loanAmount = total.subtract(downPayment);

//         // // === 3. CHUYỂN LÃI SUẤT TỪ % SANG SỐ THẬP PHÂN ===
//         // // Ví dụ interestRate = 1.2 → 0.012
//         // BigDecimal r = interestRate.divide(BigDecimal.valueOf(100), 10,
//         // RoundingMode.HALF_UP);

//         // // === 4. TÍNH TIỀN PHẢI TRẢ MỖI KỲ THEO CÔNG THỨC PMT ===
//         // // Công thức: LoanAmount × r × (1 + r)^n / ((1 + r)^n − 1)

//         // // (1 + r)
//         // BigDecimal onePlusR = r.add(BigDecimal.ONE);

//         // // (1 + r)^n
//         // BigDecimal pow = onePlusR.pow(n);

//         // // Tử số: loanAmount × r × (1+r)^n
//         // BigDecimal numerator = loanAmount.multiply(r).multiply(pow);

//         // // Mẫu số: (1+r)^n − 1
//         // BigDecimal denominator = pow.subtract(BigDecimal.ONE);

//         // // Tiền mỗi kỳ cần trả (gốc + lãi)
//         // BigDecimal installmentAmount = numerator.divide(denominator, 2,
//         // RoundingMode.HALF_UP);

//         // for (int i = 1; i <= n; i++) {
//         // payment = new Payment();

//         // payment.setNumberCycle(i);

//         // payment.setAmount(installmentAmount);

//         // payment.setPaymentDate(LocalDateTime.now().plusMonths(i));

//         // payment.setStatus(PaymentStatusEnum.UNPAID);

//         // payment.setPenaltyAmount(BigDecimal.valueOf(0));

//         // payment.setOrder(order);

//         // order.getPayments().add(payment);
//         // }

//         // }

//         // orderRepository.save(order);

//         // return new OrderResponseDTO(order);

//         // // if (paymentType.equals("THANG")) {

//         // // Payment payment = new Payment();

//         // // }
//         // }

//         @Transactional
//         public OrderResponseDTO updatePaymentStatus(Integer orderId, PaymentRequestDTO paymentRequestDTO) {

//                 Order order = orderRepository.findByIdFetchAllRelations(orderId)
//                                 .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

//                 for (Payment payment : order.getPayments()) {
//                         if (payment.getStatus() == PaymentStatusEnum.UNPAID) {
//                                 payment.setStatus(PaymentStatusEnum.PAID);
//                                 payment.setPaymentDate(LocalDateTime.now());

//                                 payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());

//                                 if (paymentRequestDTO.getPaymentMethod() == PaymentMethodEnum.VNPAY) {
//                                         payment.setVnpayCode(paymentRequestDTO.getVnpayCode());
//                                 }

//                                 break;
//                         }
//                 }

//                 orderRepository.save(order);

//                 // return null;

//                 return new OrderResponseDTO(order);
//         }

//         @Transactional
//         public OrderResponseDTO createDelivery(Integer orderId, VehicleDeliveryRequestDTO vehicledeliveryRequestDTO) {

//                 Order order = orderRepository.findById(orderId)
//                                 .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
//                 if (order.getStatus() == OrderStatusEnum.PENDING) {
//                         order.setStatus(OrderStatusEnum.PENDING_DELIVERY);

//                         Employee employee = employeeRepository.findById(vehicledeliveryRequestDTO.getEmployeeId())
//                                         .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//                         order.setEmployee(employee);

//                         VehicleDelivery vehicleDelivery = new VehicleDelivery();

//                         vehicleDelivery.setEmployee(employee);

//                         vehicleDelivery.setOderId(order);

//                         vehicleDelivery.setAddress(vehicledeliveryRequestDTO.getAddress());

//                         vehicleDelivery.setPhoneNumber(vehicledeliveryRequestDTO.getPhoneNumber());

//                         vehicleDelivery.setName(vehicledeliveryRequestDTO.getName());

//                         vehicleDelivery.setStatus(VehicleDeliveryStatusEnum.DELIVERED);

//                         // vehicleDeliveryRepository.save(vehicleDelivery);

//                         order.setVehicleDelivery(vehicleDelivery);

//                         orderRepository.save(order);

//                         return new OrderResponseDTO(order);
//                 } else {
//                         throw new ConflictException("Order no status");
//                 }

//         }

//         @Transactional
//         public OrderResponseDTO updateDelivery(Integer orderId, VehicleDeliveryStatusEnum vehicleDeliveryStatusEnum) {

//                 Order order = orderRepository.findById(orderId)
//                                 .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

//                 order.getVehicleDelivery().setStatus(vehicleDeliveryStatusEnum);

//                 if (vehicleDeliveryStatusEnum == VehicleDeliveryStatusEnum.DELIVERING) {
//                         WarehouseReleaseNote warehouseReleaseNote = new WarehouseReleaseNote();

//                         warehouseReleaseNote.setEmployeeId(order.getEmployee());

//                         warehouseReleaseNote.setOder(order);

//                         warehouseReleaseNote.setTotalAmount(order.getTotalAmount());

//                         warehouseReleaseNote.setReleaseDate(LocalDateTime.now());

//                         // warehouseReleaseNote.get

//                         // Map<Integer, Integer> vehicleDetailQuantity = order.getOrderDetails()
//                         // .stream()
//                         // .collect(Collectors.toMap(
//                         // orderDetail -> orderDetail.

//                         Map<Integer, Integer> vehicleDetailQuantity = order.getOrderDetails()
//                                         .stream()
//                                         .collect(Collectors.toMap(
//                                                         orderDetail -> orderDetail.getVehicleTypeDetail()
//                                                                         .getVehicleTypeDetailId(),
//                                                         orderDetail -> orderDetail.getQuantity()));

//                         for (Entry<Integer, Integer> entry : vehicleDetailQuantity.entrySet()) {
//                                 Set<Vehicle> vehicles = vehicleRepository.findAvailableVehicles(
//                                                 VehicleStatusEnum.IN_STOCK,
//                                                 order.getEmployee().getAgency().getAgencyId(),
//                                                 entry.getKey());

//                                 if (vehicles.size() < entry.getValue()) {
//                                         throw new ConflictException("Không đủ xe");
//                                 }

//                                 vehicles.stream().forEach((vehicle) -> vehicle.setStatus(VehicleStatusEnum.SOLD));

//                                 warehouseReleaseNote.getVehicles().addAll(vehicles);
//                         }

//                         warehouseReleaseNoteRepository.save(warehouseReleaseNote);
//                         orderRepository.save(order);

//                         return new OrderResponseDTO(order);
//                 } else if (vehicleDeliveryStatusEnum == VehicleDeliveryStatusEnum.DELIVERED) {
//                         order.setStatus(OrderStatusEnum.DELIVERED);
//                         orderRepository.save(order);
//                         return new OrderResponseDTO(order);
//                 }
//                 return null;
//         }

//         @Transactional
//         public OrderResponseDTO createOrder(Integer employeeId, OrderRequestDTO orderRequestDTO) {

//                 Order order = new Order();
//                 order.setNotes(orderRequestDTO.getNotes());

//                 Agency agency = agencyRepository.findById(orderRequestDTO.getAgencyId())
//                                 .orElseThrow(() -> new ResourceNotFoundException("Agency Not Found"));

//                 Employee employee = employeeRepository.findById(employeeId)
//                                 .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));

//                 order.setAgency(employee.getAgency());

//                 order.setDealderAgency(agency);
//                 order.setEmployee(employee);
//                 order.setStatus(OrderStatusEnum.DELIVERED);
//                 order.setType(OrderTypeEnum.AGENCY);

//                 orderRepository.save(order);

//                 Map<Integer, Integer> vehicleTypeDetailMap = orderRequestDTO.getOrderDetails()
//                                 .stream()
//                                 // .map((orderDetailRequest) -> orderDetailRequest
//                                 // .getVehicleTypeDetailId())
//                                 // .collect(Collectors.toList()));
//                                 .collect(Collectors.toMap((orderDetailRequest) -> orderDetailRequest
//                                                 .getVehicleTypeDetailId(),
//                                                 (orderDetailRequest) -> orderDetailRequest.getQuantity()));

//                 if (vehicleTypeDetailMap.size() != orderRequestDTO.getOrderDetails().size()) {
//                         throw new ResourceNotFoundException("VehicleTypeDetail Not Found");
//                 }

//                 List<VehicleTypeDetail> vehicleTypeDetails = vehicleTypeDetailRepository
//                                 .findAllById(vehicleTypeDetailMap.keySet());

//                 if (vehicleTypeDetails.size() != orderRequestDTO.getOrderDetails().size()) {
//                         throw new ResourceNotFoundException("VehicleTypeDetail Not Found");
//                 }

//                 Map<Integer, BigDecimal> vehicleTypeDetailPriceMap = agencyWholesalePriceRepository
//                                 .findByAgency_AgencyIdAndVehicleTypeDetail_VehicleTypeDetailIdIn(agency.getAgencyId(),
//                                                 vehicleTypeDetailMap.keySet())
//                                 .stream()
//                                 .collect(Collectors.toMap(
//                                                 agencyWholesalePrice -> agencyWholesalePrice.getVehicleTypeDetail()
//                                                                 .getVehicleTypeDetailId(),
//                                                 agencyWholesalePrice -> agencyWholesalePrice.getWholesalePrice()));

//                 if (vehicleTypeDetailPriceMap.size() != orderRequestDTO.getOrderDetails().size()) {
//                         throw new ResourceNotFoundException("VehicleTypeDetail Not Found");
//                 }

//                 BigDecimal total = BigDecimal.ZERO;

//                 for (VehicleTypeDetail vehicleTypeDetail : vehicleTypeDetails) {
//                         OrderDetail orderDetail = new OrderDetail();

//                         orderDetail.setVehicleTypeDetail(vehicleTypeDetail);

//                         orderDetail.setQuantity(vehicleTypeDetailMap.get(vehicleTypeDetail.getVehicleTypeDetailId()));

//                         orderDetail.setPrice(vehicleTypeDetailPriceMap.get(vehicleTypeDetail.getVehicleTypeDetailId()));

//                         orderDetail.setOrder(order);

//                         total = total.add(
//                                         orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));

//                         order.getOrderDetails().add(orderDetail);

//                 }

//                 order.setOriginaAmount(total);

//                 Policy policy = policyRepository.findByAgency_AgesncyId(agency.getAgencyId()).orElse(null);

//                 if (policy != null) {
//                         if (policy.getPolicyType() == PolicyTypeEnum.QUANTITY) {
//                                 Integer count = order.getOrderDetails().stream()
//                                                 .reduce(0,
//                                                                 (sum, orderDetail) -> sum + orderDetail.getQuantity(),
//                                                                 Integer::sum);
//                                 List<QuantityDiscountLevel> quantityDiscountLevels = policy.getQuantityDiscountLevel();

//                                 QuantityDiscountLevel discount = quantityDiscountLevels.stream()
//                                                 .filter(level -> count >= level.getQuantityFrom()
//                                                                 && count <= level.getQuantityTo())
//                                                 .findAny()
//                                                 .orElse(null);
//                                 BigDecimal discountPercentage = (discount != null) ? discount.getDiscountPercentage()
//                                                 : BigDecimal.ZERO;

//                                 BigDecimal discountAmount = order.getOriginaAmount()
//                                                 .multiply(discountPercentage.divide(BigDecimal.valueOf(100)));

//                                 order.setDiscountAmount(discountAmount);

//                                 order.setTotalAmount(order.getOriginaAmount().subtract(order.getDiscountAmount()));

//                         } else {
//                                 LocalDate now = LocalDate.now(); // hôm nay
//                                 LocalDate previousMonth = now.minusMonths(1); // lùi 1 tháng

//                                 int month = previousMonth.getMonthValue();
//                                 int year = previousMonth.getYear();

//                                 MonthlySales monthlySales = monthlySalesRepository.findByAgencyAndMonthAndYear(
//                                                 agency.getAgencyId(),
//                                                 month,
//                                                 year).orElse(null);

//                                 BigDecimal discountPercentage = (monthlySales != null) ? monthlySales.getSalesAmount()
//                                                 : BigDecimal.ZERO;

//                                 BigDecimal discountAmount = order.getOriginaAmount()
//                                                 .multiply(discountPercentage.divide(BigDecimal.valueOf(100)));

//                                 order.setDiscountAmount(discountAmount);

//                                 order.setTotalAmount(order.getOriginaAmount().subtract(order.getDiscountAmount()));

//                         }

//                 }

//                 orderRepository.save(order);

//                 return new OrderResponseDTO(order);

//                 // Map<Integer, Double> vehicleTypeDetailPriceMap = vehicleTypeDetailRepository
//                 // .findAllById(orderRequestDTO.getOrderDetails()
//                 // .stream()
//                 // .map((orderDetailRequest) -> orderDetailRequest
//                 // .getVehicleTypeDetailId())
//                 // .collect(Collectors.toList()))
//                 // .stream()
//                 // .collect(Collectors.toMap(null, null));

//         }
// }

package com.example.evsalesmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.Payment;
import com.example.evsalesmanagement.model.Policy;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.model.VehicleDelivery;
import com.example.evsalesmanagement.model.WarehouseReleaseNote;
import com.example.evsalesmanagement.repository.EmployeeRepository;
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
        public OrderResponseDTO getOrderById(Integer orderId) {
                Order order = orderRepository.findByIdFetchAllRelations(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợp lệ"));
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

                orderRepository.save(order);
                return new OrderResponseDTO(order);
        }

        @CachePut(value = "order", key = "#orderId")
        @Transactional
        public OrderResponseDTO updateOrderById(Integer orderId, OrderStatusEnum status, String contractNumber) {
                Order order = orderRepository.findByIdFetchAllRelations(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợplệ"));

                if (order.getStatus() == OrderStatusEnum.PAID) {
                        throw new ConflictException("Hiện tại không thể cập nhật thông tin");
                }

                order.setStatus(status != null ? status : order.getStatus());
                order.setContractNumber(contractNumber != null ? contractNumber : order.getContractNumber());
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
        public OrderResponseDTO updatePaymentStatus(Integer orderId, PaymentRequestDTO paymentRequestDTO) {
                Order order = orderRepository.findByIdFetchAllRelations(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

                for (Payment payment : order.getPayments()) {
                        if (payment.getStatus() == PaymentStatusEnum.UNPAID) {
                                payment.setStatus(PaymentStatusEnum.PAID);
                                payment.setPaymentDate(LocalDateTime.now());
                                payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());

                                if (paymentRequestDTO.getPaymentMethod() == PaymentMethodEnum.VNPAY) {
                                        payment.setVnpayCode(paymentRequestDTO.getVnpayCode());
                                }
                                break;
                        }
                }

                orderRepository.save(order);
                return new OrderResponseDTO(order);
        }

        @Transactional
        public OrderResponseDTO createDelivery(Integer orderId, VehicleDeliveryRequestDTO vehicledeliveryRequestDTO) {
                Order order = orderRepository.findById(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

                if (order.getStatus() != OrderStatusEnum.PENDING) {
                        throw new ConflictException("Order no status");
                }

                order.setStatus(OrderStatusEnum.PENDING_DELIVERY);

                Employee employee = employeeRepository.findById(vehicledeliveryRequestDTO.getEmployeeId())
                                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
                order.setEmployee(employee);

                VehicleDelivery vehicleDelivery = new VehicleDelivery();
                vehicleDelivery.setEmployee(employee);
                vehicleDelivery.setOderId(order);
                vehicleDelivery.setAddress(vehicledeliveryRequestDTO.getAddress());
                vehicleDelivery.setPhoneNumber(vehicledeliveryRequestDTO.getPhoneNumber());
                vehicleDelivery.setName(vehicledeliveryRequestDTO.getName());
                vehicleDelivery.setStatus(VehicleDeliveryStatusEnum.DELIVERED);

                order.setVehicleDelivery(vehicleDelivery);
                orderRepository.save(order);

                return new OrderResponseDTO(order);
        }

        @Transactional
        public OrderResponseDTO updateDelivery(Integer orderId, VehicleDeliveryStatusEnum vehicleDeliveryStatusEnum) {
                Order order = orderRepository.findById(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

                order.getVehicleDelivery().setStatus(vehicleDeliveryStatusEnum);

                if (vehicleDeliveryStatusEnum == VehicleDeliveryStatusEnum.DELIVERING) {
                        processWarehouseRelease(order);
                        orderRepository.save(order);
                        return new OrderResponseDTO(order);
                } else if (vehicleDeliveryStatusEnum == VehicleDeliveryStatusEnum.DELIVERED) {
                        order.setStatus(OrderStatusEnum.DELIVERED);
                        orderRepository.save(order);
                        return new OrderResponseDTO(order);
                }

                return null;
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

                orderRepository.save(order);
                return new OrderResponseDTO(order);
        }

        private PaymentStrategy getPaymentStrategy(PaymentTypeEnum paymentType, Integer paymentPlanId) {
                if (paymentType == PaymentTypeEnum.FULL_PAYMENT) {
                        return fullPaymentStrategy;
                } else if (paymentType == PaymentTypeEnum.INSTALLMENT) {
                        return installmentPaymentStrategy.withPaymentPlanId(paymentPlanId);
                }
                throw new IllegalArgumentException("Invalid payment type");
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
                                throw new ConflictException("Không đủ xe");
                        }

                        vehicles.stream()
                                        .limit(entry.getValue())
                                        .forEach(vehicle -> vehicle.setStatus(VehicleStatusEnum.SOLD));

                        warehouseReleaseNote.getVehicles().addAll(vehicles);
                }

                warehouseReleaseNoteRepository.save(warehouseReleaseNote);
        }
}