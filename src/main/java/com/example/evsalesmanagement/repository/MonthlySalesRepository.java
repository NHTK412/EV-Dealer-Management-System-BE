package com.example.evsalesmanagement.repository;

// import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.MonthlySales;

@Repository
public interface MonthlySalesRepository extends JpaRepository<MonthlySales, Integer> {

    @Query("SELECT m FROM MonthlySales m " +
            "WHERE m.agency.agencyId = :agencyId " +
            "AND FUNCTION('MONTH', m.salesMonth) = :month " +
            "AND FUNCTION('YEAR', m.salesMonth) = :year")
    Optional<MonthlySales> findByAgencyAndMonthAndYear(
            @Param("agencyId") Integer agencyId,
            @Param("month") int month,
            @Param("year") int year);
}
