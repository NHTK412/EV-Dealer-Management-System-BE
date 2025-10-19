package com.example.evsalesmanagement.model;

import com.example.evsalesmanagement.enums.HinhThucGiaiQuyet;
import com.example.evsalesmanagement.enums.TrangThaiXuLy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "XuLyPhanHoi")
public class XuLyPhanHoi extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaXuLy")
    private Integer maXuLy;

    @Column(name = "NoiDungXuLy")
    private String noiDungXuLy;

    @Enumerated(EnumType.STRING)
    @Column(name = "HinhThucGiaiQuyet", nullable=false)
    private HinhThucGiaiQuyet hinhThucGiaiQuyet;

    // @Column(name = "ThoiGian")
    // private LocalDateTime thoiGian;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai", nullable=false)
    private TrangThaiXuLy trangThai;

    @OneToOne
    @JoinColumn(name = "MaPhanHoi", unique = true)
    private PhanHoi phanHoi;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    public Integer getMaXuLy() {
        return maXuLy;
    }

    public void setMaXuLy(Integer maXuLy) {
        this.maXuLy = maXuLy;
    }

    public String getNoiDungXuLy() {
        return noiDungXuLy;
    }

    public void setNoiDungXuLy(String noiDungXuLy) {
        this.noiDungXuLy = noiDungXuLy;
    }

   public HinhThucGiaiQuyet getHinhThucGiaiQuyet() {
        return hinhThucGiaiQuyet;
    }

    public void setHinhThucGiaiQuyet(HinhThucGiaiQuyet hinhThucGiaiQuyet) {
        this.hinhThucGiaiQuyet = hinhThucGiaiQuyet;
    }

    // public LocalDateTime getThoiGian() {
    // return thoiGian;
    // }

    // public void setThoiGian(LocalDateTime thoiGian) {
    // this.thoiGian = thoiGian;
    // }

     public TrangThaiXuLy getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiXuLy trangThai) {
        this.trangThai = trangThai;
    }


    public PhanHoi getPhanHoi() {
        return phanHoi;
    }

    public void setPhanHoi(PhanHoi phanHoi) {
        this.phanHoi = phanHoi;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}