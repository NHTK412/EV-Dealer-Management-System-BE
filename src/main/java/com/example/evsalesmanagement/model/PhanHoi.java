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
@Table(name = "PhanHoi")
public class PhanHoi extends GhiNhanThoiGian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhanHoi")
    private Integer maPhanHoi;

    @Column(name = "TieuDePhanHoi")
    private String tieuDePhanHoi;

    @Column(name = "NoiDungPhanHoi")
    private String noiDungPhanHoi;

    // @Column(name = "ThoiGian")
    // private LocalDateTime thoiGian;

    @Column(name = "TrangThai")
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "MaKhachHang")
    private KhachHang khachHang;

    // @OneToOne(mappedBy = "phanHoi")
    // private XuLyPhanHoi xuLyPhanHoi;

    public Integer getMaPhanHoi() {
        return maPhanHoi;
    }

    public void setMaPhanHoi(Integer maPhanHoi) {
        this.maPhanHoi = maPhanHoi;
    }

    public String getTieuDePhanHoi() {
        return tieuDePhanHoi;
    }

    public void setTieuDePhanHoi(String tieuDePhanHoi) {
        this.tieuDePhanHoi = tieuDePhanHoi;
    }

    public String getNoiDungPhanHoi() {
        return noiDungPhanHoi;
    }

    public void setNoiDungPhanHoi(String noiDungPhanHoi) {
        this.noiDungPhanHoi = noiDungPhanHoi;
    }

    // public LocalDateTime getThoiGian() {
    //     return thoiGian;
    // }

    // public void setThoiGian(LocalDateTime thoiGian) {
    //     this.thoiGian = thoiGian;
    // }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}