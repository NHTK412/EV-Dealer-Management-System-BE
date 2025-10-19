package com.example.evsalesmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO response danh sách phản hồi (1 bảng to chứa toàn bộ các phản hồi con)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhanHoiResponse {

    private Integer maPhanHoi;
    private String tieuDePhanHoi;
    private String noiDungPhanHoi;
    private String trangThai;
    
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // private LocalDateTime ngayTao;
    
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // private LocalDateTime ngayCapNhat;
    
    // Thông tin khách hàng
    // private Integer maKhachHang;
    private String tenKhachHang;
    // private String emailKhachHang;
    // private String soDienThoaiKhachHang;

    public PhanHoiResponse() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PhanHoiResponse response = new PhanHoiResponse();

        public Builder maPhanHoi(Integer maPhanHoi) {
            response.maPhanHoi = maPhanHoi;
            return this;
        }

        public Builder tieuDePhanHoi(String tieuDePhanHoi) {
            response.tieuDePhanHoi = tieuDePhanHoi;
            return this;
        }

        public Builder noiDungPhanHoi(String noiDungPhanHoi) {
            response.noiDungPhanHoi = noiDungPhanHoi;
            return this;
        }

        public Builder trangThai(String trangThai) {
            response.trangThai = trangThai;
            return this;
        }

        // public Builder ngayTao(LocalDateTime ngayTao) {
        //     response.ngayTao = ngayTao;
        //     return this;
        // }

        // public Builder ngayCapNhat(LocalDateTime ngayCapNhat) {
        //     response.ngayCapNhat = ngayCapNhat;
        //     return this;
        // }

        // public Builder maKhachHang(Integer maKhachHang) {
        //     response.maKhachHang = maKhachHang;
        //     return this;
        // }

        public Builder tenKhachHang(String tenKhachHang) {
            response.tenKhachHang = tenKhachHang;
            return this;
        }

        // public Builder emailKhachHang(String emailKhachHang) {
        //     response.emailKhachHang = emailKhachHang;
        //     return this;
        // }

        // public Builder soDienThoaiKhachHang(String soDienThoaiKhachHang) {
        //     response.soDienThoaiKhachHang = soDienThoaiKhachHang;
        //     return this;
        // }

        public PhanHoiResponse build() {
            return response;
        }
    }

    // Getters
    public Integer getMaPhanHoi() { return maPhanHoi; }
    public String getTieuDePhanHoi() { return tieuDePhanHoi; }
    public String getNoiDungPhanHoi() { return noiDungPhanHoi; }
    public String getTrangThai() { return trangThai; }
    // public LocalDateTime getNgayTao() { return ngayTao; }
    // public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }
    // public Integer getMaKhachHang() { return maKhachHang; }
    public String getTenKhachHang() { return tenKhachHang; }
    // public String getEmailKhachHang() { return emailKhachHang; }
    // public String getSoDienThoaiKhachHang() { return soDienThoaiKhachHang; }
}