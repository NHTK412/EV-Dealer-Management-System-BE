package com.example.evsalesmanagement.dto.employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.evsalesmanagement.enums.EmployeePositionEnum;
import com.example.evsalesmanagement.enums.GenderEnum;
import com.example.evsalesmanagement.model.Employee;

public class EmployeeResponseDTO {

    private Integer employeeId;
    private String employeeName;
    private GenderEnum gender;
    private LocalDate birthDate;
    private String phoneNumber;
    private String email;
    private String address;
    private EmployeePositionEnum position;
    private Integer agencyId;
    private String agencyName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public EmployeeResponseDTO() {
    }

    public EmployeeResponseDTO(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.employeeName = employee.getEmployeeName();
        this.gender = employee.getGender();
        this.birthDate = employee.getBirthDate();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
        this.address = employee.getAddress();
        this.position = employee.getPosition();

        if (employee.getAgency() != null) {
            this.agencyId = employee.getAgency().getAgencyId();
            this.agencyName = employee.getAgency().getAgencyName();
        }

        this.createdAt = employee.getCreateAt();
        this.updatedAt = employee.getUpdateAt();
    }

    // Getters and Setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

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

    public EmployeePositionEnum getPosition() {
        return position;
    }

    public void setPosition(EmployeePositionEnum position) {
        this.position = position;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}