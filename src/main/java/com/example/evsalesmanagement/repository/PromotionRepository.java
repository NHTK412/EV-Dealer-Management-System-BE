package com.example.evsalesmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
        // JOIN FETCH p.vehicleDetails v
        // JOIN FETCH p.agencies a
        @Query("""
                            SELECT p
                            FROM Promotion p
                            JOIN p.vehicleDetails v
                            JOIN p.agencies a
                            WHERE a.agencyId = :agencyId AND v.vehicleTypeDetailId = :vehicleTypeDetailId
                            ORDER BY p.discountPercent DESC

                        """)
        List<Promotion> getPromotionsByAgencyIdAndVehicleDetailsId(
                        @Param("agencyId") Integer agencyId,
                        @Param("vehicleTypeDetailId") Integer vehicleTypeDetailId);

}
