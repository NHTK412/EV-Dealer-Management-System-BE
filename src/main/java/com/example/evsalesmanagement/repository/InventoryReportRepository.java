package com.example.evsalesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.dto.inventoryreport.InventoryReportRequestDTO;
import com.example.evsalesmanagement.dto.inventoryreport.InventoryReportResponseDTO;
import com.example.evsalesmanagement.model.Vehicle;

@Repository
public interface InventoryReportRepository extends JpaRepository<Vehicle, Integer> {

    @Query("SELECT new com.example.evsalesmanagement.dto.InventoryReportResponseDTO(" +
            "  vt.vehicleTypeId, vt.vehicleTypeName, vt.manufactureYear, " +
            "  vtd.vehicleTypeDetailId, vtd.version, vtd.color, vtd.price, " +
            "  a.agencyName, COUNT(v)) " +
            "FROM Vehicle v " +
            "LEFT JOIN v.agency a " +
            "LEFT JOIN v.vehicleTypeDetail vtd " +
            "LEFT JOIN v.vehicleTypeDetail.vehicleType vt " +
            "WHERE (:#{#request.agencyId} IS NULL OR a.agencyId = :#{#request.agencyId}) " +
            "  AND (:#{#request.vehicleTypeId} IS NULL OR vt.vehicleTypeId = :#{#request.vehicleTypeId}) " +
            "  AND (:#{#request.status} IS NULL OR v.status = :#{#request.status}) " +
            "  AND (:#{#request.fromDate} IS NULL OR v.createAt >= :#{#request.fromDate}) " +
            "  AND (:#{#request.toDate} IS NULL OR v.createAt <= :#{#request.toDate}) " +
            "GROUP BY vt.vehicleTypeId, vt.vehicleTypeName, vt.manufactureYear, " +
            "  vtd.vehicleTypeDetailId, vtd.version, vtd.color, vtd.price, a.agencyName " +
            "ORDER BY vt.vehicleTypeName, vtd.version, a.agencyName")
    List<InventoryReportResponseDTO> getInventoryReportGrouped(@Param("request") InventoryReportRequestDTO request);
}