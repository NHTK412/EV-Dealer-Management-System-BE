package com.example.evsalesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.ChiTietYeuCau;

@Repository
public interface ChiTietYeuCauRepository extends JpaRepository<ChiTietYeuCau, ChiTietYeuCau.ChiTietYeuCauId> {
    List<ChiTietYeuCau> findByYeuCauNhapHang_MaYeuCau(Integer maYeuCau);

    void deleteByYeuCauNhapHang_MaYeuCau(Integer maYeuCau);

}
