package com.example.evsalesmanagement.repository;

// import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.VehicleStatusEnum;
import com.example.evsalesmanagement.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query("""
                SELECT v FROM Vehicle v
                WHERE v.status = :status
                        AND v.agency.agencyId = :agencyId
                        AND v.vehicleTypeDetail.vehicleTypeDetailId = :vehicleTypeDetailId
            """)
    Set<Vehicle> findAvailableVehicles(
            @Param("status") VehicleStatusEnum status,
            @Param("agencyId") Integer agencyId,
            @Param("vehicleTypeDetailId") Integer vehicleTypeDetailId);
}
