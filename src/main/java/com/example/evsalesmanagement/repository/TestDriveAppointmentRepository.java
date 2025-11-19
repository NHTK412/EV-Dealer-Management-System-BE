package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.model.TestDriveAppointment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestDriveAppointmentRepository extends JpaRepository<TestDriveAppointment, Integer> {
    
    @Query("SELECT a FROM TestDriveAppointment a JOIN FETCH a.customer")
    List<TestDriveAppointment> findAllWithDetails();
    
    @Query("SELECT a FROM TestDriveAppointment a JOIN FETCH a.customer WHERE LOWER(a.status) = LOWER(:status)")
    List<TestDriveAppointment> findByStatusIgnoreCaseWithDetails(String status);
    
    @Query("SELECT t FROM TestDriveAppointment t WHERE LOWER(t.status) = LOWER(:status)")
    Page<TestDriveAppointment> findByStatusIgnoreCase(String status, Pageable pageable);

    @Query("SELECT a FROM TestDriveAppointment a JOIN FETCH a.customer WHERE a.testDriveAppointmentId = :id")
    Optional<TestDriveAppointment> findByIdWithCustomer(Integer id);
}