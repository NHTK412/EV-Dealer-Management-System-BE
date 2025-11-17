package com.example.evsalesmanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.RoleEnum;
import com.example.evsalesmanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  boolean existsByPhoneNumber(String phoneNumber);

  boolean existsByEmail(String email);

  boolean existsByPhoneNumberAndEmployeeIdNot(String phoneNumber, Integer employeeId);

  boolean existsByEmailAndEmployeeIdNot(String email, Integer employeeId);

  Page<Employee> findByRole(RoleEnum role, Pageable pageable);

  Page<Employee> findByAgency_AgencyId(Integer agencyId, Pageable pageable);

  long countByAgency_AgencyId(Integer agencyId);

  long countByRole(RoleEnum role);

  Optional<Employee> findByUsername(String username);
}
