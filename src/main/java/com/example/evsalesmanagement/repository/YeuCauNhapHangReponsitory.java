package com.example.evsalesmanagement.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.YeuCauNhapHang;

@Repository
public interface YeuCauNhapHangReponsitory extends JpaRepository<YeuCauNhapHang, Integer> {
    Page<YeuCauNhapHang> findByNhanVien_MaNhanVien(Integer maNhaNVien, Pageable pageable);

    // List<YeuCauNhapHang> findByNhanVien_DaiLy_MaDaiLy(Integer maDaiLy);

}
