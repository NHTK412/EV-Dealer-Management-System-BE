package com.example.evsalesmanagement.model;

import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.AccountRoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;

//TaiKhoan = Account
@Entity
@Table(name = "Account")
public class Account extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountId")
    private Integer accountId;

    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private AccountRoleEnum role;

    @Column(name = "Status")
    private String status;

    // @Column(name = "NgayTao")
    // private LocalDateTime ngayTao;

    @Column(name = "LastLoginTime")
    private LocalDateTime lastLoginTime;

    @OneToOne
    @JoinColumn(name = "EmployeeId", unique = true)
    private Employee employee;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountRoleEnum getRole() {
        return role;
    }

    public void setRole(AccountRoleEnum role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // public LocalDateTime getNgayTao() {
    // return ngayTao;
    // }

    // public void setNgayTao(LocalDateTime ngayTao) {
    // this.ngayTao = ngayTao;
    // }

}
