package com.example.evsalesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.YeuCauNhapHang;


@Repository
public interface YeuCauNhapHangReponsitory extends JpaRepository<YeuCauNhapHang, Integer>{
    List<YeuCauNhapHang> findByNhanVien_MaNhanVien(Integer maNhaNVien);
}
