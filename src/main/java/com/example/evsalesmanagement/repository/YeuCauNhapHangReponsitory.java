package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.YeuCauNhapHang;


@Repository
public interface YeuCauNhapHangReponsitory extends JpaRepository<YeuCauNhapHang, Integer>{

}
