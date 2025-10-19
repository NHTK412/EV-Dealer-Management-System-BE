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

@Entity
@Table(name = "NhanVien")
public class NhanVien extends GhiNhanThoiGian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNhanVien")
    private Integer maNhanVien;

    @Column(name = "TenNhanVien", nullable = false)
    private String tenNhanVien;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "ChucVu")
    private String chucVu;

    @ManyToOne
    @JoinColumn(name = "MaDaiLy")
    private DaiLy daiLy;

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

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public DaiLy getDaiLy() {
        return daiLy;
    }

    public void setDaiLy(DaiLy daiLy) {
        this.daiLy = daiLy;
    }
}