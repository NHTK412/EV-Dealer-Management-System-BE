package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.model.TestDriveAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDriveAppointmentRepository extends JpaRepository<TestDriveAppointment, Integer> {

    @Query("SELECT t FROM TestDriveAppointment t WHERE LOWER(t.status) = LOWER('Pending')")
    List<TestDriveAppointment> findPendingAppointments();

    @Query("SELECT t FROM TestDriveAppointment t WHERE LOWER(t.status) = LOWER('Scheduled')")
    List<TestDriveAppointment> findScheduledAppointments();

    @Query("SELECT t FROM TestDriveAppointment t WHERE LOWER(t.status) = LOWER('Completed')")
    List<TestDriveAppointment> findCompletedAppointments();
}
