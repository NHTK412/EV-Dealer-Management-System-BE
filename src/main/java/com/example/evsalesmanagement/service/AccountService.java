package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.security.CustomerUserDetails;

@Service
public class AccountService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public UserDetails getAccountByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        return new CustomerUserDetails(employee);
    }
}
