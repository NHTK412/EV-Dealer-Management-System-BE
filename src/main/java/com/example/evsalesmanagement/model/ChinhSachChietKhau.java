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
@Table(name = "ChinhSachChietKhau")
public class ChinhSachChietKhau extends GhiNhanThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChinhSach")
    private Integer maChinhSach;

    @Column(name = "LoaiChietKhau")
    private String loaiChietKhau;

    @Column(name = "MucChietKhau")
    private BigDecimal mucChietKhau;

    @Column(name = "DieuKienApDung")
    private String dieuKienApDung;

    @Column(name = "NgayBatDau")
    private LocalDateTime ngayBatDau;

    @Column(name = "NgayKetThuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "TrangThai")
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "MaDaiLy")
    private DaiLy daiLy;

    // @OneToMany(mappedBy = "chinhSachChietKhau")
    // private List<BacChietKhauSoLuong> bacChietKhauSoLuongs = new ArrayList<>();

    // @OneToMany(mappedBy = "chinhSachChietKhau")
    // private List<BacChietKhauDoanhSo> bacChietKhauDoanhSos = new ArrayList<>();

    public Integer getMaChinhSach() {
        return maChinhSach;
    }

    public void setMaChinhSach(Integer maChinhSach) {
        this.maChinhSach = maChinhSach;
    }

    public String getLoaiChietKhau() {
        return loaiChietKhau;
    }

    public void setLoaiChietKhau(String loaiChietKhau) {
        this.loaiChietKhau = loaiChietKhau;
    }

    public BigDecimal getMucChietKhau() {
        return mucChietKhau;
    }

    public void setMucChietKhau(BigDecimal mucChietKhau) {
        this.mucChietKhau = mucChietKhau;
    }

    public String getDieuKienApDung() {
        return dieuKienApDung;
    }

    public void setDieuKienApDung(String dieuKienApDung) {
        this.dieuKienApDung = dieuKienApDung;
    }

    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
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

    public DaiLy getDaiLy() {
        return daiLy;
    }

    public void setDaiLy(DaiLy daiLy) {
        this.daiLy = daiLy;
    }
}