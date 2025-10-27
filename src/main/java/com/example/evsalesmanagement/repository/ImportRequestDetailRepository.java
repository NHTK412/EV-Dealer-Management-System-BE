package com.example.evsalesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.ImportRequestDetail;

@Repository
public interface ImportRequestDetailRepository extends JpaRepository<ImportRequestDetail, ImportRequestDetail.ImportRequestDetailId> {
    List<ImportRequestDetail> findByImportRequest_ImportRequestId(Integer importRequestId);

    void deleteByImportRequest_ImportRequestId(Integer importRequestId);

}
