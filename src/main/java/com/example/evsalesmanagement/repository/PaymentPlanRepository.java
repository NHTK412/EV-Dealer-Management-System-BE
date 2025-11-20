package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.PaymentPlan;


@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Integer> {

}
