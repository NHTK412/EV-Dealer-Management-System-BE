package com.example.evsalesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.VehicleTypeDetail;

@Repository
public interface VehicleTypeDetailRepository extends JpaRepository<VehicleTypeDetail, Integer> {

    @Query("""
            SELECT DISTINCT vtd
            FROM VehicleTypeDetail vtd
            JOIN FETCH vtd.vehicleType vt
            WHERE vtd.id IN :ids
            """)
    List<VehicleTypeDetail> getAllByIdWithVehicleType(@Param("ids") List<Integer> ids);
}
