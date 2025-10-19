package com.example.evsalesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "YeuCauNhapHang")
public class YeuCauNhapHang extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaYeuCau")
    private Integer maYeuCau;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "GhiChu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    // @OneToMany(mappedBy = "yeuCauNhapHang")
    // private List<ChiTietYeuCau> chiTietYeuCaus = new ArrayList<>();

    public Integer getMaYeuCau() {
        return maYeuCau;
    }

    public void setMaYeuCau(Integer maYeuCau) {
        this.maYeuCau = maYeuCau;
    }

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

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}