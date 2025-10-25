package com.example.evsalesmanagement.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "QuotationDetail")
public class QuotationDetail extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuotationDetailId")
    private Integer quotationDetailId;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Discount")
    private BigDecimal discount;

    @Column(name = "RegistrationTax")
    private BigDecimal registrationTax;

    @Column(name = "LicensePlateFee")
    private BigDecimal licensePlateFee;

    @Column(name = "InspectionFee")
    private BigDecimal inspectionFee;

    @Column(name = "CompulsoryInsurance")
    private BigDecimal compulsoryInsurance;

    @Column(name = "VehiclePhysicalInsurance")
    private BigDecimal vehiclePhysicalInsurance;

    @Column(name = "RoadMaintenanceFee")
    private BigDecimal roadMaintenanceFee;

    @Column(name = "VehicleRegistrationServiceFee")
    private BigDecimal vehicleRegistrationServiceFee;

    @Column(name = "DiscountPercentage")
    private BigDecimal discountPercentage;

    @Column(name = "WholesalePrice")
    private BigDecimal wholesalePrice;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "QuotationId")
    private Quotation quotation;

    @ManyToOne
    @JoinColumn(name = "VehicleDetail")
    private VehicleDetail vehicleTypeDetail;

    public Integer getQuotationDetailId() {
        return quotationDetailId;
    }

    public void setQuotationDetailId(Integer quotationDetailId) {
        this.quotationDetailId = quotationDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getRegistrationTax() {
        return registrationTax;
    }

    public void setRegistrationTax(BigDecimal registrationTax) {
        this.registrationTax = registrationTax;
    }

    public BigDecimal getLicensePlateFee() {
        return licensePlateFee;
    }

    public void setLicensePlateFee(BigDecimal licensePlateFee) {
        this.licensePlateFee = licensePlateFee;
    }

    public BigDecimal getInspectionFee() {
        return inspectionFee;
    }

    public void setInspectionFee(BigDecimal inspectionFee) {
        this.inspectionFee = inspectionFee;
    }

    public BigDecimal getCompulsoryInsurance() {
        return compulsoryInsurance;
    }

    public void setCompulsoryInsurance(BigDecimal compulsoryInsurance) {
        this.compulsoryInsurance = compulsoryInsurance;
    }

    public BigDecimal getVehiclePhysicalInsurance() {
        return vehiclePhysicalInsurance;
    }

    public void setVehiclePhysicalInsurance(BigDecimal vehiclePhysicalInsurance) {
        this.vehiclePhysicalInsurance = vehiclePhysicalInsurance;
    }

    public BigDecimal getRoadMaintenanceFee() {
        return roadMaintenanceFee;
    }

    public void setRoadMaintenanceFee(BigDecimal roadMaintenanceFee) {
        this.roadMaintenanceFee = roadMaintenanceFee;
    }

    public BigDecimal getVehicleRegistrationServiceFee() {
        return vehicleRegistrationServiceFee;
    }

    public void setVehicleRegistrationServiceFee(BigDecimal vehicleRegistrationServiceFee) {
        this.vehicleRegistrationServiceFee = vehicleRegistrationServiceFee;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public VehicleDetail getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleDetail vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }
}
