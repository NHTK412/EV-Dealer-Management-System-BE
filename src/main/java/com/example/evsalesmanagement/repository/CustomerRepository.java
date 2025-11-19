package com.example.evsalesmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.CustomerMembershipLevelEnum;
import com.example.evsalesmanagement.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmailAndCustomerIdNot(String email, Integer customerId);

    boolean existsByPhoneNumberAndCustomerIdNot(String phoneNumber, Integer customerId);

    Page<Customer> findByMembershipLevel(CustomerMembershipLevelEnum membershipLevel, Pageable pageable);

    long countByMembershipLevel(CustomerMembershipLevelEnum membershipLevel);
}
