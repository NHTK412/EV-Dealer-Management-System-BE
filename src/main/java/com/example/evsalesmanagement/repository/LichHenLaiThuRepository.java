package com.example.evsalesmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.evsalesmanagement.model.LichHenLaiThu;

@Repository
public interface LichHenLaiThuRepository extends JpaRepository<LichHenLaiThu, Integer> {
    
    // Lấy tất cả lịch hẹn với thông tin khách hàng
    @Query(value = "SELECT " +
                   "lh.ma_lich_hen, " +
                   "kh.ten_khach_hang, " +
                   "lh.ngay_hen, " +
                   "lh.gio_hen, " +
                   "lh.ma_xe, " +
                   "lh.trang_thai " +
                   "FROM lich_hen_lai_thu lh " +
                   "JOIN khach_hang kh ON kh.ma_khach_hang = lh.ma_khach_hang " +
                   "ORDER BY lh.ngay_hen DESC",
           nativeQuery = true)
    List<Object[]> layTatCaLichHen();
    
    // Lấy danh sách lịch hẹn theo trạng thái chờ xác nhận
    @Query(value = "SELECT " +
                   "kh.ten_khach_hang, " +
                   "lh.ngay_hen, " +
                   "lh.gio_hen, " +
                   "lh.ma_xe, " +
                   "lh.trang_thai " +
                   "FROM lich_hen_lai_thu lh " +
                   "JOIN khach_hang kh ON kh.ma_khach_hang = lh.ma_khach_hang " +
                   "WHERE lh.trang_thai = 'Chờ xác nhận' " +
                   "ORDER BY lh.ngay_hen DESC",
           nativeQuery = true)
    List<Object[]> layLichHenChoXacNhan();
    
    // Lấy danh sách lịch hẹn theo trạng thái đã xác nhận
    @Query(value = "SELECT " +
                   "kh.ten_khach_hang, " +
                   "lh.ngay_hen, " +
                   "lh.gio_hen, " +
                   "lh.ma_xe, " +
                   "lh.trang_thai " +
                   "FROM lich_hen_lai_thu lh " +
                   "JOIN khach_hang kh ON kh.ma_khach_hang = lh.ma_khach_hang " +
                   "WHERE lh.trang_thai = 'Đã xác nhận' " +
                   "ORDER BY lh.ngay_hen DESC",
           nativeQuery = true)
    List<Object[]> layLichHenDaXacNhan();
}