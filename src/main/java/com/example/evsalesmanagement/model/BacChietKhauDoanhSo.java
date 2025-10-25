// package com.example.evsalesmanagement.model;

// import java.math.BigDecimal;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "BacChietKhauDoanhSo")
// public class BacChietKhauDoanhSo extends GhiNhanThoiGian {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "MaBacChietKhauDoanhSo")
//     private Integer maBacChietKhauDoanhSo;

//     @Column(name = "DoanhSoTu")
//     private Integer doanhSoTu;

//     @Column(name = "DoanhSoDen")
//     private Integer doanhSoDen;

//     @Column(name = "PhanTramChietKhau")
//     private BigDecimal phanTramChietKhau;

//     @ManyToOne
//     @JoinColumn(name = "MaChinhSach")
//     private ChinhSachChietKhau chinhSachChietKhau;

//     public Integer getMaBacChietKhauDoanhSo() {
//         return maBacChietKhauDoanhSo;
//     }

//     public void setMaBacChietKhauDoanhSo(Integer maBacChietKhauDoanhSo) {
//         this.maBacChietKhauDoanhSo = maBacChietKhauDoanhSo;
//     }

//     public Integer getDoanhSoTu() {
//         return doanhSoTu;
//     }

//     public void setDoanhSoTu(Integer doanhSoTu) {
//         this.doanhSoTu = doanhSoTu;
//     }

//     public Integer getDoanhSoDen() {
//         return doanhSoDen;
//     }

//     public void setDoanhSoDen(Integer doanhSoDen) {
//         this.doanhSoDen = doanhSoDen;
//     }

//     public BigDecimal getPhanTramChietKhau() {
//         return phanTramChietKhau;
//     }

//     public void setPhanTramChietKhau(BigDecimal phanTramChietKhau) {
//         this.phanTramChietKhau = phanTramChietKhau;
//     }

//     public ChinhSachChietKhau getChinhSachChietKhau() {
//         return chinhSachChietKhau;
//     }

//     public void setChinhSachChietKhau(ChinhSachChietKhau chinhSachChietKhau) {
//         this.chinhSachChietKhau = chinhSachChietKhau;
//     }
// }