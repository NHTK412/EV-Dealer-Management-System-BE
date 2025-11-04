package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.evsalesmanagement.model.WarehouseReceipt;

@Repository
public interface WarehouseReceiptRepository extends JpaRepository<WarehouseReceipt, Integer> {
}
