package com.example.evsalesmanagement.dto.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.CustomerMembershipLevelEnum;
import com.example.evsalesmanagement.enums.GenderEnum;
import com.example.evsalesmanagement.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerResponseDTO {

    private Integer customerId;
    private String customerName;
    private GenderEnum gender;
    private LocalDate birthDate;
    private String phoneNumber;
    private String email;
    private String address;
    private CustomerMembershipLevelEnum membershipLevel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    // Constructors
    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerName = customer.getCustomerName();
        this.gender = customer.getGender();
        this.birthDate = customer.getBirthDate();
        this.phoneNumber = customer.getPhoneNumber();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.membershipLevel = customer.getMembershipLevel();
        this.createdAt = customer.getCreateAt();
        this.updatedAt = customer.getUpdateAt();
    }

    // Getters and Setters
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

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerMembershipLevelEnum getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(CustomerMembershipLevelEnum membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}