package com.example.evsalesmanagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.model.Order;

@Repository
public interface RevenueReportRepository extends JpaRepository<Order, Integer> {

    List<Order> findAll();

    List<Order> findByAgency_AgencyId(Integer agencyId);

    List<Order> findByStatus(OrderStatusEnum status);

    List<Order> findByAgency_AgencyIdAndStatus(Integer agencyId, OrderStatusEnum status);

    List<Order> findByCreateAtBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<Order> findByAgency_AgencyIdAndCreateAtBetween(Integer agencyId, LocalDateTime fromDate, LocalDateTime toDate);

    List<Order> findByStatusAndCreateAtBetween(OrderStatusEnum status, LocalDateTime fromDate, LocalDateTime toDate);

    List<Order> findByAgency_AgencyIdAndStatusAndCreateAtBetween(Integer agencyId, OrderStatusEnum status,
            LocalDateTime fromDate, LocalDateTime toDate);
}