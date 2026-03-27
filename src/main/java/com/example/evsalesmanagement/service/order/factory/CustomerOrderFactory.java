package com.example.evsalesmanagement.service.order.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.enums.OrderTypeEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.OrderDetail;
import com.example.evsalesmanagement.model.QuotationDetail;
import com.example.evsalesmanagement.model.Quote;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.QuoteRepository;

@Component
public class CustomerOrderFactory implements OrderFactory {


    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Integer quoteId;
    private Integer employeeId;
    private String notes;

    // EmployeeRepository employeeRepository) {
    // this.quoteRepository = quoteRepository;
    // this.employeeRepository = employeeRepository;

    public CustomerOrderFactory withQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
        return this;
    }

    public CustomerOrderFactory withEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public CustomerOrderFactory withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public Order createOrder() {
        Quote quote = quoteRepository.findById(quoteId)
            .orElseThrow(() -> new ResourceNotFoundException("Quote not found with ID: " + quoteId));

        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        Customer customer = quote.getCustomer();

        Order order = new Order();
        order.setAgency(employee.getAgency());
        order.setNotes(notes);
        order.setStatus(OrderStatusEnum.PENDING);
        order.setType(OrderTypeEnum.CUSTOMER);
        order.setEmployee(employee);
        order.setCustomer(customer);

        addOrderDetailsFromQuote(order, quote.getQuotationDetails());

        order.setOriginaAmount(quote.getTotalAmount());
        order.setTotalAmount(quote.getTotalAmount());

        return order;
    }

    private void addOrderDetailsFromQuote(Order order, List<QuotationDetail> quotationDetails) {
        for (QuotationDetail quotationDetail : quotationDetails) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(quotationDetail.getQuantity());
            orderDetail.setDiscount(quotationDetail.getDiscount());
            orderDetail.setRegistrationTax(quotationDetail.getRegistrationTax());
            orderDetail.setLicensePlateFee(quotationDetail.getLicensePlateFee());
            orderDetail.setRegistrartionFee(quotationDetail.getRegistrartionFee());
            orderDetail.setCompulsoryInsurance(quotationDetail.getCompulsoryInsurance());
            orderDetail.setRoadMaintenanceMees(quotationDetail.getRoadMaintenanceMees());
            orderDetail.setVehicleRegistrationServiceFee(quotationDetail.getVehicleRegistrationServiceFee());
            orderDetail.setDiscountPercentage(quotationDetail.getDiscountPercentage());
            orderDetail.setPrice(quotationDetail.getPrice());
            orderDetail.setTotalAmount(quotationDetail.getTotalAmount());
            orderDetail.setVehicleTypeDetail(quotationDetail.getVehicleTypeDetail());
            orderDetail.setMaterialInsurance(quotationDetail.getMaterialInsurance());
            orderDetail.setOrder(order);
            order.getOrderDetails().add(orderDetail);
        }
    }
}