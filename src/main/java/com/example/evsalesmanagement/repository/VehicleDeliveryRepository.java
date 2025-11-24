package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.model.VehicleDelivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleDeliveryRepository extends JpaRepository<VehicleDelivery, Integer> {

    // Lấy danh sách đơn giao hàng của đại lý với phân trang
    @Query("SELECT vd FROM VehicleDelivery vd " +
            "JOIN vd.order o " +
            "WHERE o.agency.agencyId = :agencyId " +
            "ORDER BY vd.createAt DESC")
    Page<VehicleDelivery> findByAgencyId(@Param("agencyId") Integer agencyId, Pageable pageable);

    // Lấy chi tiết đơn giao hàng kèm thông tin liên quan
    @Query("SELECT vd FROM VehicleDelivery vd " +
            "LEFT JOIN FETCH vd.order o " +
            "LEFT JOIN FETCH o.agency " +
            "LEFT JOIN FETCH vd.employee " +
            "WHERE vd.vehicleDeliveryId = :vehicleDeliveryId")
    Optional<VehicleDelivery> findByIdWithDetails(@Param("vehicleDeliveryId") Integer vehicleDeliveryId);

    // Lấy chi tiết đơn giao hàng của đại lý
    @Query("SELECT vd FROM VehicleDelivery vd " +
            "LEFT JOIN FETCH vd.order o " +
            "LEFT JOIN FETCH o.agency a " +
            "LEFT JOIN FETCH vd.employee " +
            "WHERE vd.vehicleDeliveryId = :vehicleDeliveryId " +
            "AND a.agencyId = :agencyId")
    Optional<VehicleDelivery> findByIdAndAgencyId(
            @Param("vehicleDeliveryId") Integer vehicleDeliveryId,
            @Param("agencyId") Integer agencyId);
}