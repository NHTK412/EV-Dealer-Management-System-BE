package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    
}
