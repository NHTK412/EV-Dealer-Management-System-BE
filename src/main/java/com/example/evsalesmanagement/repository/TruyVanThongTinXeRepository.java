package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.evsalesmanagement.model.VehicleTypeDetail;

@Repository
public interface TruyVanThongTinXeRepository extends JpaRepository<VehicleTypeDetail, Integer> {
    // lấy thông tin toàn bộ xe
    @Query(value = "SELECT " +
                   "lx.ma_loai_xe, " +
                   "lx.ten_loai_xe, " +
                   "ct.hinh_anh_xe, " +
                   "lx.nam_san_xuat, " +
                   "ct.gia_ban, " +
                   "lx.mo_ta " +
                   "FROM chi_tiet_loai_xe ct " +
                   "JOIN loai_xe lx ON lx.ma_loai_xe = ct.ma_loai_xe " +
                   "ORDER BY ct.ma_loai_xe",
           nativeQuery = true)
    List<Object[]> layThongTinXe();
    
    // lấy thông tin từng chi tiết loại xe
   @Query(value = "SELECT " +
               "lx.ma_loai_xe, " +
               "lx.ten_loai_xe, " +
               "ct.hinh_anh_xe, " +
               "lx.nam_san_xuat, " +
               "ct.gia_ban, " +
               "lx.mo_ta, " +
               "ct.cau_hinh, " +
               "ct.mau_sac, " +
               "ct.phien_ban " +
               "FROM chi_tiet_loai_xe ct " +
               "JOIN loai_xe lx ON lx.ma_loai_xe = ct.ma_loai_xe " +
               "ORDER BY ct.ma_loai_xe",
       nativeQuery = true)
    List<Object[]> layThongTinChiTietXe();
}