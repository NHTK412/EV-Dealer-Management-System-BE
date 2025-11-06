package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.model.SalesDiscountLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDiscountLevelRepository extends JpaRepository<SalesDiscountLevel, Integer> {
	java.util.List<SalesDiscountLevel> findByPolicy(com.example.evsalesmanagement.model.Policy policy);

}
