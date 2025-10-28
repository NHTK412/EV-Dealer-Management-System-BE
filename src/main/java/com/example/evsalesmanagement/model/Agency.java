package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Agency")
public class Agency extends Base {
    //ma dai ly = agencyId

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgencyId")
    private Integer agencyId;
    
    //ten dai ly = agencyName
    @Column(name = "Agencyname", nullable = false)
    private String agencyName;

    //dia chi = address
    @Column(name = "Address")
    private String address;

    //so dien thoai = phoneNumber
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    //trang thai = status
    @Column(name = "Email")
    private String email;

    // trang thai = status
    @Column(name = "Status")
    private String status;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // @OneToMany(mappedBy = "daiLy")
    // private List<NhanVien> nhanViens = new ArrayList<>();

    // @OneToMany(mappedBy = "daiLy")
    // private List<Xe> xes = new ArrayList<>();

    // @OneToMany(mappedBy = "daiLy")
    // private List<DonHang> donHangs = new ArrayList<>();

    // @OneToMany(mappedBy = "daiLy")
    // private List<PhieuNhapKho> phieuNhapKhos = new ArrayList<>();

    // @OneToMany(mappedBy = "daiLy")
    // private List<GiaSiDaiLy> giaSiDaiLys = new ArrayList<>();

    // @OneToMany(mappedBy = "daiLy")
    // private List<ChinhSachChietKhau> chinhSachChietKhaus = new ArrayList<>();

    // @ManyToMany(mappedBy = "daiLys")
    // private List<KhuyenMai> khuyenMais = new ArrayList<>();

    
}

