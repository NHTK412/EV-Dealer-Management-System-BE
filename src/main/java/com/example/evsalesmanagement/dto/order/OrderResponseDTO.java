package com.example.evsalesmanagement.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.evsalesmanagement.dto.orderdetail.OrderDetailResponseDTO;
import com.example.evsalesmanagement.model.Order;


public class OrderResponseDTO {

    private Integer orderId;

    private String notes;

    private String contractNumber;

    private Integer employeeId;

    private String employeeName;

    private String employeePhoneNumber;

    private String employeeEmail;

    private BigDecimal totalAmount;

    private String status;

    private Integer customerId;

    private String customerName;

    private String customerEmail;

    private String customerPhoneNumber;

    private String customerAddress;

    private Integer agencyId;

    private String agencyName;

    private List<OrderDetailResponseDTO> orderDetailResponseDTOs = new ArrayList<>();

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

}
