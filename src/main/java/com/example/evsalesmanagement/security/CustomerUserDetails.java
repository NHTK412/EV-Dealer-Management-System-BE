package com.example.evsalesmanagement.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// import com.example.evsalesmanagement.model.Account;
import com.example.evsalesmanagement.model.Employee;

public class CustomerUserDetails implements UserDetails {

    private Employee employee;

// <<<<<<< HEAD
    public CustomerUserDetails(Employee employee) {
        this.employee = employee;
// =======
//     public CustomerUserDetails() {
//     }

//     public CustomerUserDetails(Account account) {
//         this.account = account;
// >>>>>>> feat/Khang/cauHinhRedis
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(employee.getRole().name()));
    }

    @Override
    public String getPassword() {
        return this.employee.getPassword();
    }

    @Override
    public String getUsername() {
        return this.employee.getUsername();
    }

    public Integer getEmployeeId() {
        return this.employee.getEmployeeId();
    }

    public Integer getAgencyId() {
        return this.employee.getAgency().getAgencyId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
