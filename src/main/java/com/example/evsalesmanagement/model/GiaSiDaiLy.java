package com.example.evsalesmanagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GiaSiDaiLy")
public class GiaSiDaiLy extends GhiNhanThoiGian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaGiaSiDaiLy")
    private Integer maGiaSiDaiLy;

    @Column(name = "GiaSi")
    private BigDecimal giaSi;

    @Column(name = "SoLuongToiThieu")
    private Integer soLuongToiThieu;

    @Column(name = "NgayApDung")
    private LocalDateTime ngayApDung;

    @Column(name = "NgayKetThuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "TrangThai")
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "MaDaiLy")
    private Agency daiLy;

    @ManyToOne
    @JoinColumn(name = "MaChiTietLoaiXe")
    private ChiTietLoaiXe chiTietLoaiXe;

    public Integer getMaGiaSiDaiLy() {
        return maGiaSiDaiLy;
    }

    public void setMaGiaSiDaiLy(Integer maGiaSiDaiLy) {
        this.maGiaSiDaiLy = maGiaSiDaiLy;
    }

    public BigDecimal getGiaSi() {
        return giaSi;
    }

    public void setGiaSi(BigDecimal giaSi) {
        this.giaSi = giaSi;
    }

    public Integer getSoLuongToiThieu() {
        return soLuongToiThieu;
    }

    public void setSoLuongToiThieu(Integer soLuongToiThieu) {
        this.soLuongToiThieu = soLuongToiThieu;
    }

    public LocalDateTime getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(LocalDateTime ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Agency getDaiLy() {
        return daiLy;
    }

    public void setDaiLy(Agency daiLy) {
        this.daiLy = daiLy;
    }

    public ChiTietLoaiXe getChiTietLoaiXe() {
        return chiTietLoaiXe;
    }

    public void setChiTietLoaiXe(ChiTietLoaiXe chiTietLoaiXe) {
        this.chiTietLoaiXe = chiTietLoaiXe;
    }
}