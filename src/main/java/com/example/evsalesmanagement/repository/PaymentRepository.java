package com.example.evsalesmanagement.repository;

import com.example.evsalesmanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE LOWER(p.status) = LOWER('Pending')")
    List<Payment> findPendingPayments();

    @Query("SELECT p FROM Payment p WHERE LOWER(p.status) = LOWER('Paid')")
    List<Payment> findPaidPayments();
}
