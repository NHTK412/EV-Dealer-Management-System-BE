package com.example.evsalesmanagement.dto.account;

import com.example.evsalesmanagement.model.Account;

public class AccountResponseDTO {

    private Integer accountId;

    private String username;

    private String role;

    private String status;

    private Integer employeeId;

    public AccountResponseDTO() {
    }

    public AccountResponseDTO(Account account) {
        this.accountId = account.getAccountId();
        this.username = account.getUsername();
        this.role = account.getRole();
        this.status = account.getStatus();
        this.employeeId = account.getEmployee().getEmployeeId();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

}
