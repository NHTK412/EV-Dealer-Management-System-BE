package com.example.evsalesmanagement.dto;


import java.time.LocalDate;

import com.example.evsalesmanagement.model.Employee;




public class EmployeeDTO {
    private Integer employeeId;

    private String employeeName;

    private String phoneNumber;

    private LocalDate birthDate;

    private String email;

    private String position;

    private Integer agencyId;

    private String agencyName;


    public EmployeeDTO(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.employeeName = employee.getEmployeeName();
        this.phoneNumber = employee.getPhoneNumber();
        this.birthDate = employee.getBirthDate();
        this.email = employee.getEmail();
        this.position = employee.getPosition();
        this.agencyId = employee.getAgency().getAgencyId();
        this.agencyName = employee.getAgency().getAgencyName();

    }

    public EmployeeDTO(Integer employeeId, String employeeName, String phoneNumber, LocalDate birthDate, String email, String position, Integer agencyId, String agencyName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.position = position;
        this.agencyId = agencyId;
        this.agencyName = agencyName;
    }
    

    public EmployeeDTO() {
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
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

    
}
