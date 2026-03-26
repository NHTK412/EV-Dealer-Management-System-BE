package com.example.evsalesmanagement.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.PaymentStatusEnum;
import com.example.evsalesmanagement.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // List<Payment> findPendingPayments();

    // List<Payment> findPaidPayments();

    List<Payment> findByStatus(PaymentStatusEnum status);

    List<Payment> findByOrder_OrderId(Integer orderId);

    Optional<Payment> findByVnpayCode(String vnpayCode);

    @Query("SELECT p FROM Payment p WHERE p.status = :status AND p.dueDate < :currentDate")
    List<Payment> findOverduePayments(@Param("status") PaymentStatusEnum status,
            @Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT p FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    List<Payment> findByPaymentDateRange(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    Page<Payment> findByOrder_Customer_PhoneNumber(String phoneNumber, Pageable pageable);
}
