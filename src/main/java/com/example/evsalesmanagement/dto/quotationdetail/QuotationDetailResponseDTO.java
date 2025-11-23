package com.example.evsalesmanagement.dto.quotationdetail;

import java.math.BigDecimal;

import com.example.evsalesmanagement.model.QuotationDetail;

public class QuotationDetailResponseDTO {

    private Integer quotationDetailId;

    private Integer vehicleTypeDetailId;

    private String vehicleConfiguration;

    private String vehicleColor;

    private String vehicleVersion;

    private String vehicleFeatures;

    private BigDecimal vehiclePrice;

    private String vehicleTypeName;

    private Integer quantity;

    private BigDecimal discount;

    private BigDecimal registrationTax;

    private BigDecimal licensePlateFee;

    private BigDecimal registrartionFee;

    private BigDecimal compulsoryInsurance;

    private BigDecimal materialInsurance;

    private BigDecimal roadMaintenanceMees;

    private BigDecimal vehicleRegistrationServiceFee;

    private BigDecimal discountPercentage;

    private BigDecimal wholesalePrice;

    private BigDecimal totalAmount;

    public QuotationDetailResponseDTO(QuotationDetail quotationDetail) {
        this.quotationDetailId = quotationDetail.getQuotationDetailId();
        this.vehicleTypeDetailId = quotationDetail.getVehicleTypeDetail().getVehicleTypeDetailId();
        this.vehicleConfiguration = quotationDetail.getVehicleTypeDetail().getConfiguration();
        this.vehicleColor = quotationDetail.getVehicleTypeDetail().getColor();
        this.vehicleVersion = quotationDetail.getVehicleTypeDetail().getVersion();
        this.vehicleFeatures = quotationDetail.getVehicleTypeDetail().getFeatures();
        this.vehiclePrice = quotationDetail.getVehicleTypeDetail().getPrice();
        this.vehicleTypeName = quotationDetail.getVehicleTypeDetail().getVehicleType().getVehicleTypeName();
        this.quantity = quotationDetail.getQuantity();
        this.discount = quotationDetail.getDiscount();
        this.registrationTax = quotationDetail.getRegistrationTax();
        this.licensePlateFee = quotationDetail.getLicensePlateFee();
        this.registrartionFee = quotationDetail.getRegistrartionFee();
        this.compulsoryInsurance = quotationDetail.getCompulsoryInsurance();
        this.materialInsurance = quotationDetail.getMaterialInsurance();
        this.roadMaintenanceMees = quotationDetail.getRoadMaintenanceMees();
        this.vehicleRegistrationServiceFee = quotationDetail.getVehicleRegistrationServiceFee();
        this.discountPercentage = quotationDetail.getDiscountPercentage();
        this.wholesalePrice = quotationDetail.getPrice();
        this.totalAmount = quotationDetail.getTotalAmount();
    }

    public QuotationDetailResponseDTO() {
    }

    public Integer getQuotationDetailId() {
        return quotationDetailId;
    }

    public void setQuotationDetailId(Integer quotationDetailId) {
        this.quotationDetailId = quotationDetailId;
    }

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
    }

    public String getVehicleConfiguration() {
        return vehicleConfiguration;
    }

    public void setVehicleConfiguration(String vehicleConfiguration) {
        this.vehicleConfiguration = vehicleConfiguration;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleVersion() {
        return vehicleVersion;
    }

    public void setVehicleVersion(String vehicleVersion) {
        this.vehicleVersion = vehicleVersion;
    }

    public String getVehicleFeatures() {
        return vehicleFeatures;
    }

    public void setVehicleFeatures(String vehicleFeatures) {
        this.vehicleFeatures = vehicleFeatures;
    }

    public BigDecimal getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(BigDecimal vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
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

}
