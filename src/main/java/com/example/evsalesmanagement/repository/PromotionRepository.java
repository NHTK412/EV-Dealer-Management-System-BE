package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

}
