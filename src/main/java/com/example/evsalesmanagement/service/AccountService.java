package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Account;
import com.example.evsalesmanagement.repository.AccountRepository;
import com.example.evsalesmanagement.security.CustomerUserDetails;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public UserDetails getAccountByUsername(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username không tồn tại"));

        return new CustomerUserDetails(account);
    }

}
