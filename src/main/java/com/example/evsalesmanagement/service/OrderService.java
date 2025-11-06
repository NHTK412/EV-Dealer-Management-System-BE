package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.evsalesmanagement.dto.order.OrderFromQuoteRequestDTO;
import com.example.evsalesmanagement.dto.order.OrderResponseDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.OrderDetail;
import com.example.evsalesmanagement.model.QuotationDetail;
import com.example.evsalesmanagement.model.Quote;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.CustomerRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.OrderRepository;
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

        @Autowired
        private AgencyRepository agencyRepository;

        @Autowired
        private CustomerRepository customerRepository;

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

        @Transactional
        public OrderResponseDTO createOrderFromQuotation(OrderFromQuoteRequestDTO orderFromQuoteRequestDTO) {

                Quote quote = quoteRepository.findById(orderFromQuoteRequestDTO.getQuoteId())
                                .orElseThrow(() -> new ResourceNotFoundException("Mã báo giá không hợp lệ"));

                Employee employee = employeeRepository.findById(orderFromQuoteRequestDTO.getEmployeeId())
                                .orElseThrow(() -> new ResourceNotFoundException("Mã nhân viên không hợp lệ"));

                // Agency agency =
                // agencyRepository.findById(orderFromQuoteRequestDTO.getAgencyId())
                // .orElseThrow(() -> new ResourceNotFoundException("Mã đại lý không hợp lệ"));

                Customer customer = customerRepository.findById(orderFromQuoteRequestDTO.getCustomerId())
                                .orElseThrow(() -> new ResourceNotFoundException("Mã khách hàng không hợp lệ"));

                Order order = new Order();

                order.setNotes(orderFromQuoteRequestDTO.getNotes());

                order.setStatus("CREATE");

                order.setType("CUSTOMER");

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

        @Transactional
        public OrderResponseDTO updateOrderById(Integer orderId, String status, String contractNumber) {
                Order order = orderRepository.findByIdFetchAllRelations(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn hàng không hợplệ"));

                if (order.getStatus().equals("DONE")) {

                        order.setStatus(status != null ? status : order.getStatus());

                        order.setContractNumber(contractNumber != null ? contractNumber : order.getContractNumber());
                }

                orderRepository.save(order);

                return new OrderResponseDTO(order);
        }
}
