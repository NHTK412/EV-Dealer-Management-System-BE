package com.example.evsalesmanagement.model;

import java.time.LocalDate;

import com.example.evsalesmanagement.enums.GenderEnum;
import com.example.evsalesmanagement.enums.CustomerMembershipLevelEnum;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private Integer customerId;

    @Column(name = "CustomerName", nullable = false)
    private String customerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private GenderEnum gender;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "MembershipLevel")
    private CustomerMembershipLevelEnum membershipLevel;

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






    // this.ngayTao = ngayTao;
}