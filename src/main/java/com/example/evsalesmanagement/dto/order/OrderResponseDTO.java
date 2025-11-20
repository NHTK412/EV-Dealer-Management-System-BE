package com.example.evsalesmanagement.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.evsalesmanagement.dto.orderdetail.OrderDetailResponseDTO;
import com.example.evsalesmanagement.dto.payment.PaymentResponseDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.VehicleDelivery;
import com.fasterxml.jackson.annotation.JsonInclude;

public class OrderResponseDTO {

    private Integer orderId;

    private String notes;

    private String contractNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer employeeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String employeeName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String employeePhoneNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String employeeEmail;

    private BigDecimal totalAmount;

    private String status;

    private Integer customerId;

    private String customerName;

    private String customerEmail;

    private String customerPhoneNumber;

    private String customerAddress;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer agencyId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String agencyName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String agencyAddress;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String agencyPhone;

    private List<OrderDetailResponseDTO> orderDetailResponseDTOs = new ArrayList<>();

    private Set<PaymentResponseDTO> paymentResponseDTOs = new HashSet<>();

    private VehicleDeliveryResponseDTO vehicleDeliveryResponseDTO;


    public OrderResponseDTO(Order order) {
        this.orderId = order.getOrderId();
        this.notes = order.getNotes();
        this.contractNumber = order.getContractNumber();
        this.status = order.getStatus().getDisplayName();
        this.employeeId = order.getEmployee().getEmployeeId();
        this.employeeName = order.getEmployee().getEmployeeName();
        this.employeePhoneNumber = order.getEmployee().getPhoneNumber();
        this.employeeEmail = order.getEmployee().getEmail();
        this.totalAmount = order.getTotalAmount();

        if (order.getCustomer() != null) {
            this.customerId = order.getCustomer().getCustomerId();
            this.customerName = order.getCustomer().getCustomerName();
            this.customerEmail = order.getCustomer().getEmail();
            this.customerPhoneNumber = order.getCustomer().getPhoneNumber();
            this.customerAddress = order.getCustomer().getAddress();
        }
        if (order.getAgency() != null) {
            this.agencyId = order.getAgency().getAgencyId();
            this.agencyName = order.getAgency().getAgencyName();
            this.agencyAddress = order.getAgency().getAddress();
            this.agencyPhone = order.getAgency().getPhoneNumber();
        }

        if (order.getPayments() != null) {
            this.paymentResponseDTOs = order.getPayments()
                    .stream()
                    .map(payment -> {
                        return new PaymentResponseDTO(payment);
                    })
                    .collect(Collectors.toSet());
        }

        if (order.getVehicleDelivery() != null)
        {
            // this.orderDetailResponseDTOs.add(new OrderDetailResponseDTO(order.getVehicleDelivery()));
            this.vehicleDeliveryResponseDTO = new VehicleDeliveryResponseDTO(order.getVehicleDelivery());

        }

        this.orderDetailResponseDTOs = order.getOrderDetails().stream().map(orderDetail -> {
            return new OrderDetailResponseDTO(orderDetail);
        }).toList();
    }

    public OrderResponseDTO() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public List<OrderDetailResponseDTO> getOrderDetailResponseDTOs() {
        return orderDetailResponseDTOs;
    }

    public void setOrderDetailResponseDTOs(List<OrderDetailResponseDTO> orderDetailResponseDTOs) {
        this.orderDetailResponseDTOs = orderDetailResponseDTOs;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public String getAgencyPhone() {
        return agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        this.agencyPhone = agencyPhone;
    }

    public Set<PaymentResponseDTO> getPaymentResponseDTOs() {
        return paymentResponseDTOs;
    }

    public void setPaymentResponseDTOs(Set<PaymentResponseDTO> paymentResponseDTOs) {
        this.paymentResponseDTOs = paymentResponseDTOs;
    }

    public VehicleDeliveryResponseDTO getVehicleDeliveryResponseDTO() {
        return vehicleDeliveryResponseDTO;
    }

    public void setVehicleDeliveryResponseDTO(VehicleDeliveryResponseDTO vehicleDeliveryResponseDTO) {
        this.vehicleDeliveryResponseDTO = vehicleDeliveryResponseDTO;
    }

    



}
