package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.VehicleTypeDetail;

@Repository
public interface VehicleTypeDetailRepository extends JpaRepository<VehicleTypeDetail, Integer> {

}
