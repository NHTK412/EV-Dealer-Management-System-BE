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
@Table(name = "ChiTietLoaiXe")
public class ChiTietLoaiXe extends GhiNhanThoiGian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChiTietLoaiXe")
    private Integer maChiTietLoaiXe;

    @Column(name = "HinhAnhXe")
    private String hinhAnhXe;

    @Column(name = "CauHinh")
    private String cauHinh;

    @Column(name = "MauSac")
    private String mauSac;

    @Column(name = "PhienBan")
    private String phienBan;

    @Column(name = "TinhNang")
    private String tinhNang;

    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @ManyToOne
    @JoinColumn(name = "MaLoaiXe")
    private LoaiXe loaiXe;

    // @OneToMany(mappedBy = "chiTietLoaiXe")
    // private List<Xe> xes = new ArrayList<>();

    // @OneToMany(mappedBy = "chiTietLoaiXe")
    // private List<ChiTietBaoGia> chiTietBaoGias = new ArrayList<>();

    // @ManyToMany(mappedBy = "chiTietLoaiXes")
    // private List<KhuyenMai> khuyenMais = new ArrayList<>();

    // @OneToMany(mappedBy = "chiTietLoaiXe")
    // private List<GiaSiDaiLy> giaSiDaiLys = new ArrayList<>();

    // @OneToMany(mappedBy = "chiTietLoaiXe")
    // private List<ChiTietYeuCau> chiTietYeuCaus = new ArrayList<>();

    public Integer getMaChiTietLoaiXe() {
        return maChiTietLoaiXe;
    }

    public void setMaChiTietLoaiXe(Integer maChiTietLoaiXe) {
        this.maChiTietLoaiXe = maChiTietLoaiXe;
    }

    public String getCauHinh() {
        return cauHinh;
    }

    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getPhienBan() {
        return phienBan;
    }

    public void setPhienBan(String phienBan) {
        this.phienBan = phienBan;
    }

    public String getTinhNang() {
        return tinhNang;
    }

    public void setTinhNang(String tinhNang) {
        this.tinhNang = tinhNang;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public LoaiXe getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getHinhAnhXe() {
        return hinhAnhXe;
    }

    public void setHinhAnhXe(String hinhAnhXe) {
        this.hinhAnhXe = hinhAnhXe;
    }

}