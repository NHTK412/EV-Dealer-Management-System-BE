
package com.example.evsalesmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer> {
}


