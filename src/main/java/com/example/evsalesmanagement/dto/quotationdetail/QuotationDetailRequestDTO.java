package com.example.evsalesmanagement.dto.quotationdetail;

import java.math.BigDecimal;

public class QuotationDetailRequestDTO {

    private Integer vehicleTypeDetailId;

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

    public Integer getVehicleTypeDetailId() {
        return vehicleTypeDetailId;
    }

    public void setVehicleTypeDetailId(Integer vehicleTypeDetailId) {
        this.vehicleTypeDetailId = vehicleTypeDetailId;
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

    
}
