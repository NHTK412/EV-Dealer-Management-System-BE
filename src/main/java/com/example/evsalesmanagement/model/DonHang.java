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

@Entity
@Table(name = "DonHang")
public class DonHang extends GhiNhanThoiGian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDonHang")
    private Integer maDonHang;

    @Column(name = "SoHopDong")
    private String soHopDong;

    // @Column(name = "NgayTao")
    // private LocalDateTime ngayTao;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "TongTienDonHang")
    private BigDecimal tongTienDonHang;

    @ManyToOne
    @JoinColumn(name = "MaKhachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "MaDaiLy")
    private DaiLy daiLy;

    // @OneToOne(mappedBy = "donHang")
    // private BaoGia baoGia;

    // @OneToMany(mappedBy = "donHang")
    // private List<ThanhToan> thanhToans = new ArrayList<>();

    // @OneToOne(mappedBy = "donHang")
    // private GiaoXe giaoXe;

    // @OneToOne(mappedBy = "donHang")
    // private PhieuXuatKho phieuXuatKho;

    public Integer getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Integer maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getSoHopDong() {
        return soHopDong;
    }

    public void setSoHopDong(String soHopDong) {
        this.soHopDong = soHopDong;
    }

    // public LocalDateTime getNgayTao() {
    //     return ngayTao;
    // }

    // public void setNgayTao(LocalDateTime ngayTao) {
    //     this.ngayTao = ngayTao;
    // }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public BigDecimal getTongTienDonHang() {
        return tongTienDonHang;
    }

    public void setTongTienDonHang(BigDecimal tongTienDonHang) {
        this.tongTienDonHang = tongTienDonHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public DaiLy getDaiLy() {
        return daiLy;
    }

    public void setDaiLy(DaiLy daiLy) {
        this.daiLy = daiLy;
    }

    // public BaoGia getBaoGia() {
    // return baoGia;
    // }

    // public void setBaoGia(BaoGia baoGia) {
    // this.baoGia = baoGia;
    // }

    // public List<ThanhToan> getThanhToans() {
    // return thanhToans;
    // }

    // public void setThanhToans(List<ThanhToan> thanhToans) {
    // this.thanhToans = thanhToans;
    // }

    // public GiaoXe getGiaoXe() {
    // return giaoXe;
    // }

    // public void setGiaoXe(GiaoXe giaoXe) {
    // this.giaoXe = giaoXe;
    // }

    // public PhieuXuatKho getPhieuXuatKho() {
    // return phieuXuatKho;
    // }

    // public void setPhieuXuatKho(PhieuXuatKho phieuXuatKho) {
    // this.phieuXuatKho = phieuXuatKho;
    // }
}