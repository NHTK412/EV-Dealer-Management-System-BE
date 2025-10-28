package com.example.evsalesmanagement.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//NhanVien = Employee
@Entity
@Table(name = "Employee")
public class Employee extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeId")
    private Integer employeeId;

    @Column(name = "EmployeeName", nullable = false)
    private String employeeName;

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

    // chuc vu = position

    @Column(name = "Position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "AgencyId")
    private Agency agency;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    // @OneToOne(mappedBy = "nhanVien")
    // private TaiKhoan taiKhoan;

    // @OneToMany(mappedBy = "nhanVien")
    // private List<DonHang> donHangs = new ArrayList<>();

    // @OneToMany(mappedBy = "nhanVien")
    // private List<GiaoXe> giaoXes = new ArrayList<>();

    // @OneToMany(mappedBy = "nhanVien")
    // private List<PhieuXuatKho> phieuXuatKhos = new ArrayList<>();

    // @OneToMany(mappedBy = "nhanVien")
    // private List<PhieuNhapKho> phieuNhapKhos = new ArrayList<>();

    // @OneToMany(mappedBy = "nhanVien")
    // private List<XuLyPhanHoi> xuLyPhanHois = new ArrayList<>();

    // @OneToMany(mappedBy = "nhanVien")
    // private List<YeuCauNhapHang> yeuCauNhapHangs = new ArrayList<>();

}
