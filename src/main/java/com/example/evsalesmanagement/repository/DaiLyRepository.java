
package com.example.evsalesmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.DaiLy;

@Repository
public interface DaiLyRepository extends JpaRepository<DaiLy, Integer> {
}


