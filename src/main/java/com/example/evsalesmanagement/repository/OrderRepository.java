package com.example.evsalesmanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        @Query("""
                        SELECT o
                        FROM Order o
                        LEFT JOIN FETCH o.employee e
                        LEFT JOIN FETCH o.agency a
                        WHERE a.agencyId = :agencyId
                        """)
        Page<Order> findByAgencyId(@Param("agencyId") Integer agencyId, Pageable pageable);

        @Query("""
                        SELECT o
                        FROM Order o
                        LEFT JOIN FETCH o.employee e
                        LEFT JOIN FETCH e.agency a
                        LEFT JOIN FETCH o.customer c
                        WHERE e.employeeId = :employeeId
                        """)
        Page<Order> findByEmployeeId(@Param("employeeId") Integer employeeId,
                        Pageable pageable);

        @Query("""
                        SELECT o
                        FROM Order o
                        LEFT JOIN FETCH o.customer c
                        LEFT JOIN FETCH o.agency a
                        WHERE a.agencyId = :agencyId AND c.customerId = :customerId
                        """)
        Page<Order> findByAgencyIdAndCustomerId(@Param("agencyId") Integer agencyId,
                        @Param("customerId") Integer customerId, Pageable pageable);

        // Page<Order> findByEmployee_Agency_AgencyId(Integer agencyId, Pageable
        // pageable);

        @Query("""
                        SELECT o
                        FROM Order o
                        LEFT JOIN FETCH o.customer c
                        LEFT JOIN FETCH o.agency a
                        WHERE a.agencyId = :agencyId AND c.phoneNumber = :customerPhone
                        """)
        Page<Order> findByAgencyIdAndCustomerPhone(@Param("agencyId") Integer agencyId,
                        @Param("customerPhone") String customerPhone, Pageable pageable);

        // Tìm các đơn hàng của đại lý (DealderAgencyId) đó với số điện thoại của đại lý
        // (AGency)

}
