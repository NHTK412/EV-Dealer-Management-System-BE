package com.example.evsalesmanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evsalesmanagement.enums.EmployeeStatusEnum;
import com.example.evsalesmanagement.enums.RoleEnum;
import com.example.evsalesmanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByPhoneNumberAndEmployeeIdNot(String phoneNumber, Integer employeeId);
    boolean existsByEmailAndEmployeeIdNot(String email, Integer employeeId);
    Page<Employee> findByRole(RoleEnum role, Pageable pageable);
    Page<Employee> findByAgency_AgencyId(Integer agencyId, Pageable pageable);
    long countByAgency_AgencyId(Integer agencyId);
    long countByRole(RoleEnum role);
    Optional<Employee> findByUsername(String username);
    boolean existsByEmailAndStatus(String email, EmployeeStatusEnum status);
    boolean existsByPhoneNumberAndStatus(String phoneNumber, EmployeeStatusEnum status);
    boolean existsByUsernameAndStatus(String username, EmployeeStatusEnum status);
    boolean existsByEmailAndEmployeeIdNotAndStatus(String email, Integer employeeId, EmployeeStatusEnum status);
    boolean existsByPhoneNumberAndEmployeeIdNotAndStatus(String phoneNumber, Integer employeeId, EmployeeStatusEnum status);
    Page<Employee> findByStatus(EmployeeStatusEnum status, Pageable pageable);
    Page<Employee> findByRoleAndStatus(RoleEnum role, EmployeeStatusEnum status, Pageable pageable);   
    Page<Employee> findByAgency_AgencyIdAndStatus(Integer agencyId, EmployeeStatusEnum status, Pageable pageable);  
    long countByAgency_AgencyIdAndStatus(Integer agencyId, EmployeeStatusEnum status);
    long countByRoleAndStatus(RoleEnum role, EmployeeStatusEnum status);
    Optional<Employee> findByUsernameAndStatus(String username, EmployeeStatusEnum status);
}