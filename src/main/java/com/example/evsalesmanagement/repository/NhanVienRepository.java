package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

}
