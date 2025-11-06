package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.model.QuantityDiscountLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityDiscountLevelRepository extends JpaRepository<QuantityDiscountLevel, Integer> {
	java.util.List<QuantityDiscountLevel> findByPolicy(com.example.evsalesmanagement.model.Policy policy);
}
	