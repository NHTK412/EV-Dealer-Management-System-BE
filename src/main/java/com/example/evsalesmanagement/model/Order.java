package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.enums.OrderTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer orderId;
    //
    @Column(name = "ContractNumber")
    private String contractNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "Statuss")
    private OrderStatusEnum status;

    @Column(name = "Notes")
    private String notes;

    @Column(name = "DiscountAmount")
    private BigDecimal discountAmount;

    @Column(name = "OriginalAmount")
    private BigDecimal originaAmount;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Types")
    private OrderTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "DealderAgencyId")
    private Agency dealerAgency;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agency;

    

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Payment> payments = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private VehicleDelivery vehicleDelivery;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer oderId) {
        this.orderId = oderId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderTypeEnum getType() {
        return type;
    }

    public void setType(OrderTypeEnum type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Agency getDealderAgency() {
        return dealerAgency;
    }

    public void setDealderAgency(Agency dealerAgency) {
        this.dealerAgency = dealerAgency;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public VehicleDelivery getVehicleDelivery() {
        return vehicleDelivery;
    }

    public void setVehicleDelivery(VehicleDelivery vehicleDelivery) {
        this.vehicleDelivery = vehicleDelivery;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getOriginaAmount() {
        return originaAmount;
    }

    public void setOriginaAmount(BigDecimal originaAmount) {
        this.originaAmount = originaAmount;
    }

    public Agency getDealerAgency() {
        return dealerAgency;
    }

    public void setDealerAgency(Agency dealerAgency) {
        this.dealerAgency = dealerAgency;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }






    // this.baoGia = baoGia;


    // this.thanhToans = thanhToans;


    // this.giaoXe = giaoXe;


    // this.phieuXuatKho = phieuXuatKho;
}