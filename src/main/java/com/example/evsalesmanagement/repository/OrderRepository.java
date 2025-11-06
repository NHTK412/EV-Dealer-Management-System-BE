package com.example.evsalesmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("""
            SELECT DISTINCT o
            FROM Order o
            LEFT JOIN FETCH o.customer
            LEFT JOIN FETCH o.agency
            LEFT JOIN FETCH o.employee
            LEFT JOIN FETCH o.orderDetails
            WHERE o.orderId = :orderId""")
    Optional<Order> findByIdFetchAllRelations(@Param("orderId") Integer orderId);

}
