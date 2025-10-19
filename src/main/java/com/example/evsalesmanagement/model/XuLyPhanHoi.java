package com.example.evsalesmanagement.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(name = "HinhThucGiaiQuyet")
    private String hinhThucGiaiQuyet;

    // @Column(name = "ThoiGian")
    // private LocalDateTime thoiGian;

    @Column(name = "TrangThai")
    private String trangThai;

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

    public String getHinhThucGiaiQuyet() {
        return hinhThucGiaiQuyet;
    }

    public void setHinhThucGiaiQuyet(String hinhThucGiaiQuyet) {
        this.hinhThucGiaiQuyet = hinhThucGiaiQuyet;
    }

    // public LocalDateTime getThoiGian() {
    // return thoiGian;
    // }

    // public void setThoiGian(LocalDateTime thoiGian) {
    // this.thoiGian = thoiGian;
    // }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
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