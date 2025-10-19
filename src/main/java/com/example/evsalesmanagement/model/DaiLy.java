package com.example.evsalesmanagement.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DaiLy")
public class DaiLy extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDaiLy")
    private Integer maDaiLy;

    @Column(name = "TenDaiLy", nullable = false)
    private String tenDaiLy;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "TrangThai")
    private String trangThai;

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

    public Integer getMaDaiLy() {
        return maDaiLy;
    }

    public void setMaDaiLy(Integer maDaiLy) {
        this.maDaiLy = maDaiLy;
    }

    public String getTenDaiLy() {
        return tenDaiLy;
    }

    public void setTenDaiLy(String tenDaiLy) {
        this.tenDaiLy = tenDaiLy;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}