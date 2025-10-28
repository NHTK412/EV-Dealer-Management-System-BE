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

//ChiTietBaoGia = QuotationDetail
@Entity
@Table(name = "QuotationDetail")
public class QuotationDetail extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuotationDetailId")
    private Integer quotationDetailId;
    //so luong = quantity

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Discount")
    private BigDecimal discount;
    
    //Registration tax = phi truoc ba
    @Column(name = "RegistrationTax")
    private BigDecimal registrationTax;
    
    //LicensePlate fee = phi bien so 
    @Column(name = "LicensePlateFee ")
    private BigDecimal licensePlateFee;

    //Registration fee = phi dang kiem
    @Column(name = "Registration Fee")
    private BigDecimal registrartionFee;

    //CompulsoryInsurance = bao hiem bat buoc
    @Column(name = "CompulsoryInsurance")
    private BigDecimal compulsoryInsurance;

    //MaterialInsurance = bao hiem vat chat
    @Column(name = "MaterialInsurance")
    private BigDecimal materialInsurance;

    //RoadMaintenanceMees = phi bao tri duong bo
    @Column(name = "RoadMaintenanceMees")
    private BigDecimal roadMaintenanceMees;

    //VehicleRegistrationServiceFee = phi dich vu dang ky xe
    @Column(name = "VehicleRegistrationServiceFee")
    private BigDecimal vehicleRegistrationServiceFee;

    //DiscountPercentage = phan tram chiet khau
    @Column(name = "DiscountPercentage")
    private BigDecimal discountPercentage;

    //WhoblesalePrice = gia si
    @Column(name = "WholesalePrice")
    private BigDecimal wholesalePrice;

    //total amount = tong tien 
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    //quote = bao gia
    @ManyToOne
    @JoinColumn(name = "QuoteId")
    private Quote quote;

    @ManyToOne
    @JoinColumn(name = "VehicleTypeDetailId")
    private VehicleTypeDetail vehicleTypeDetail;


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

    public BigDecimal getRegistrartionFee() {
        return registrartionFee;
    }

    public void setRegistrartionFee(BigDecimal registrartionFee) {
        this.registrartionFee = registrartionFee;

    }

    public BigDecimal getCompulsoryInsurance() {
        return compulsoryInsurance;
    }

    public void setCompulsoryInsurance(BigDecimal compulsoryInsurance) {
        this.compulsoryInsurance = compulsoryInsurance;
    }

    public BigDecimal getMaterialInsurance() {
        return materialInsurance;
    }

    public void setMaterialInsurance(BigDecimal materialInsurance) {
        this.materialInsurance = materialInsurance;
    }

    public BigDecimal getRoadMaintenanceMees() {
        return roadMaintenanceMees;
    }

    public void setRoadMaintenanceMees(BigDecimal roadMaintenanceMees) {
        this.roadMaintenanceMees = roadMaintenanceMees;

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

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public VehicleTypeDetail getVehicleTypeDetail() {
        return vehicleTypeDetail;
    }

    public void setVehicleTypeDetail(VehicleTypeDetail vehicleTypeDetail) {
        this.vehicleTypeDetail = vehicleTypeDetail;
    }


}

