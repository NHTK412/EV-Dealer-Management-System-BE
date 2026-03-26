package com.example.evsalesmanagement.dto.employee;

import java.time.LocalDate;

import com.example.evsalesmanagement.enums.GenderEnum;
import com.example.evsalesmanagement.enums.RoleEnum;
import com.example.evsalesmanagement.model.Employee;

// <<<<<<< HEAD
// =======
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
// >>>>>>> 29c2e7207eb5f529d717230bf9417c008d876d44

public class EmployeeRequestDTO {

    private String employeeName;

    private GenderEnum gender;

    private LocalDate birthDate;

    private String phoneNumber;

    private String email;

    private String address;

    @NotNull(message = "Position cannot be blank")
    private RoleEnum role;

    private Integer agencyId;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    // Constructors
    public EmployeeRequestDTO() {
    }

    public EmployeeRequestDTO(Employee employee) {
        this.employeeName = employee.getEmployeeName();
        this.gender = employee.getGender();
        this.birthDate = employee.getBirthDate();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
        this.address = employee.getAddress();
        this.role = employee.getRole();

        if (employee.getAgency() != null) {
            this.agencyId = employee.getAgency().getAgencyId();
        }
    }

    // Getters and Setters
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
