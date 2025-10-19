package com.example.evsalesmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.TrangThaiPhanHoi;
import com.example.evsalesmanagement.model.PhanHoi;

/**
 * Repository cho PhanHoi
 */
@Repository
public interface PhanHoiRepository extends JpaRepository<PhanHoi, Integer> {

    /**
     * Lấy tất cả phản hồi - sắp xếp theo ngày tạo DESC
     */
    @Query("SELECT p FROM PhanHoi p JOIN FETCH p.khachHang ORDER BY p.ngayTao DESC")
    List<PhanHoi> findAllWithKhachHang();

// @Query(value = "SELECT p FROM PhanHoi p JOIN FETCH p.khachHang ORDER BY p.ngayTao DESC",
//        countQuery = "SELECT COUNT(p) FROM PhanHoi p")
// Page<PhanHoi> findAllWithKhachHang(Pageable pageable); => vấn đề: nếu quá nhiều phản hồi => out memory => đang test dùng page <=> list 


// SELECT 
//   p.ma_phan_hoi, p.tieu_de_phan_hoi, p.noi_dung_phan_hoi, 
//   p.trang_thai, p.ngay_tao, p.ngay_cap_nhat,
//   k.ma_khach_hang, k.ten_khach_hang, k.email, k.so_dien_thoai
// FROM phan_hoi p
// INNER JOIN khach_hang k ON p.ma_khach_hang = k.ma_khach_hang
// ORDER BY p.ngay_tao DESC;
     
    
    /**
     * Lấy phản hồi theo ID 
     */
    @Query("SELECT p FROM PhanHoi p JOIN FETCH p.khachHang WHERE p.maPhanHoi = :id")
    Optional<PhanHoi> findByIdWithKhachHang(@Param("id") Integer id);

    
// SELECT 
//     p.ma_phan_hoi,
//     p.tieu_de_phan_hoi,
//     p.noi_dung_phan_hoi,
//     p.trang_thai,
//     p.ngay_tao,
//     p.ngay_cap_nhat,
//     k.ma_khach_hang,
//     k.ten_khach_hang,
//     k.email,
//     k.so_dien_thoai
// FROM phan_hoi p
// INNER JOIN khach_hang k ON p.ma_khach_hang = k.ma_khach_hang
// WHERE p.ma_phan_hoi = ?; or id
     
    
    /**
     * Lấy phản hồi kèm thông tin xử lý  - Chi tiết phản hồi
     */
    @Query("SELECT p FROM PhanHoi p " +
           "JOIN FETCH p.khachHang " +
           "LEFT JOIN FETCH p.xuLyPhanHoi xlph " +
           "LEFT JOIN FETCH xlph.nhanVien " +
           "WHERE p.maPhanHoi = :id")
    Optional<PhanHoi> findByIdWithDetails(@Param("id") Integer id);

    
// SELECT 
//     p.ma_phan_hoi, p.tieu_de_phan_hoi, p.noi_dung_phan_hoi, 
//     p.trang_thai, p.ngay_tao, p.ngay_cap_nhat,
//     k.ma_khach_hang, k.ten_khach_hang, k.email, k.so_dien_thoai,
//     xlph.ma_xu_ly, xlph.noi_dung_xu_ly, xlph.hinh_thuc_giai_quyet,
//     nv.ma_nhan_vien, nv.ten_nhan_vien, nv.email
// FROM phan_hoi p
// INNER JOIN khach_hang k ON p.ma_khach_hang = k.ma_khach_hang
// LEFT JOIN xu_ly_phan_hoi xlph ON p.ma_phan_hoi = xlph.ma_phan_hoi
// LEFT JOIN nhan_vien nv ON xlph.ma_nhan_vien = nv.ma_nhan_vien
// WHERE p.ma_phan_hoi = ?;

     
    
    /**
     * Lấy phản hồi theo trạng thái
     */
    @Query("SELECT p FROM PhanHoi p JOIN FETCH p.khachHang " +
           "WHERE p.trangThai = :trangThai ORDER BY p.ngayTao DESC")
    List<PhanHoi> findByTrangThai(@Param("trangThai") TrangThaiPhanHoi trangThai);
    
// SELECT 
//     p.ma_phan_hoi, p.tieu_de_phan_hoi, p.noi_dung_phan_hoi, 
//     p.trang_thai, p.ngay_tao, p.ngay_cap_nhat,
//     k.ma_khach_hang, k.ten_khach_hang, k.email, k.so_dien_thoai
// FROM phan_hoi p
// INNER JOIN khach_hang k ON p.ma_khach_hang = k.ma_khach_hang
// WHERE p.trang_thai = ?
// ORDER BY p.ngay_tao DESC;
     


    /**
     * Đếm phản hồi theo trạng thái: Đã xử lý: 1, Chưa xử lý: 0
     */
   Long countByTrangThai(TrangThaiPhanHoi trangThai);
    
// SELECT COUNT(p.ma_phan_hoi)
// FROM phan_hoi p
// WHERE p.trang_thai = ?;
     
}