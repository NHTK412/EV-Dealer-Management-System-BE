package com.example.evsalesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.ImportRequestDetail;

@Repository
public interface ImportRequestDetailRepository extends JpaRepository<ImportRequestDetail, Integer> {
    List<ImportRequestDetail> findByImportRequest_ImportRequestId(Integer importRequestId);

    void deleteByImportRequest_ImportRequestId(Integer importRequestId);

    @Query("""
            SELECT DISTINCT ird
            FROM ImportRequestDetail ird
            JOIN FETCH ird.vehicleTypeDetail vtd
            WHERE ird.id IN :ids
            """)
    List<ImportRequestDetail> findAllByIdWithVehicleTypeDetail(@Param("ids") List<Integer> ids);

}
    