package com.example.evsalesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

}
