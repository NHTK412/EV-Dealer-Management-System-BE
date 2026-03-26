package com.example.evsalesmanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.dto.inventoryreport.InventorySummaryDTO;
import com.example.evsalesmanagement.model.Vehicle;
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

    Page<VehicleTypeDetail> findByVehicleType_VehicleTypeId(Integer vehicleTypeId, Pageable pageable);

    // Lấy danh cách tồn kho theo đại lý của từng chi tiết loại xe nhưng vehicle
    // type và type detail nó k có mã đại lý mã đại lý nằm ở vehicle nên phải join
    // qua vehicle để lấy mã đại lý

    @Query("""
            SELECT new com.example.evsalesmanagement.dto.inventoryreport.InventorySummaryDTO(
                vtd.vehicleTypeDetailId,
                vt.vehicleTypeName,
                vtd.configuration,   
                COUNT(v),
                vtd.version,
                vtd.color,
                vtd.features
            )
            FROM Vehicle v
            JOIN v.vehicleTypeDetail vtd
            JOIN vtd.vehicleType vt
            JOIN v.agency a
            WHERE a.agencyId = :agencyId
            GROUP BY vtd.vehicleTypeDetailId, vt.vehicleTypeName, vtd.configuration,
                    vtd.version, vtd.color, vtd.features
            """)
    List<InventorySummaryDTO> findInventoryByAgencyId(@Param("agencyId") Integer agencyId);

    @Query("""
            SELECT v
            FROM Vehicle v
            JOIN v.vehicleTypeDetail vtd
            JOIN v.agency a
            WHERE a.agencyId = :agencyId
            AND vtd.vehicleTypeDetailId = :vehicleTypeDetailId
            """)
    List<Vehicle> findVehiclesByVehicleTypeDetailInInventory(
            @Param("agencyId") Integer agencyId,
            @Param("vehicleTypeDetailId") Integer vehicleTypeDetailId);
}
