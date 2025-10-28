package com.example.evsalesmanagement.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//KhachHang = Customer
@Entity
@Table(name = "Customer")
public class Customer extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private Integer customerId;

    @Column(name = "CustomerName", nullable = false)
    private String customerName;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "MembershipLevel")
    private String membershipLevel;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    // @Column(name = "NgayTao")
    // private LocalDateTime ngayTao;

    // @OneToMany(mappedBy = "khachHang")
    // private List<DonHang> donHangs = new ArrayList<>();

    // @OneToMany(mappedBy = "khachHang")
    // private List<PhanHoi> phanHois = new ArrayList<>();

    // @OneToMany(mappedBy = "khachHang")
    // private List<LichHenLaiThu> lichHenLaiThus = new ArrayList<>();

    // public LocalDateTime getNgayTao() {
    // return ngayTao;
    // }

    // public void setNgayTao(LocalDateTime ngayTao) {
    // this.ngayTao = ngayTao;
    // }
}