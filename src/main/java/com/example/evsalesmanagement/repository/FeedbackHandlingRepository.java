package com.example.evsalesmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.FeedbackHandling;

/**
 * Repository cho XuLyPhanHoi
 */
@Repository
public interface FeedbackHandlingRepository extends JpaRepository<FeedbackHandling, Integer> {
    
    // /**
    //  * Tìm xử lý theo mã phản hồi
    //  */
    // @Query("SELECT x FROM XuLyPhanHoi x " +
    //        "JOIN FETCH x.phanHoi ph " +
    //        "JOIN FETCH ph.khachHang " +
    //        "JOIN FETCH x.nhanVien " +
    //        "WHERE ph.maPhanHoi = :maPhanHoi")
    // Optional<FeedbackHandling> findByMaPhanHoiWithDetails(@Param("maPhanHoi") Integer maPhanHoi);
    
    // /**
    //  * Kiểm tra phản hồi đã được xử lý chưa
    //  */
    // boolean existsByPhanHoi_MaPhanHoi(Integer maPhanHoi);
}