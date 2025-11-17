package com.example.evsalesmanagement.dto.employee;

import java.time.LocalDate;

// import com.example.evsalesmanagement.enums.EmployeePositionEnum;
import com.example.evsalesmanagement.enums.GenderEnum;
import com.example.evsalesmanagement.enums.RoleEnum;
import com.example.evsalesmanagement.model.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeRequestDTO {

    @NotBlank(message = "Employee name cannot be blank")
    @Size(min = 2, max = 100, message = "Employee name must be between 2 and 100 characters")
    private String employeeName;

    // @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be 'Male',
    // 'Female' or 'Other'")
    private GenderEnum gender;

    @Past(message = "Birth date must be a past date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message = "Phone number is not in the correct format")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email is not valid")
    private String email;

    @Size(max = 200, message = "Address cannot exceed 200 characters")
    private String address;

    @NotBlank(message = "Position cannot be blank")
    // @Size(min = 2, max = 50, message = "Position must be between 2 and 50
    // characters")
    private RoleEnum role;

    private Integer agencyId;

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

    public void setPosition(RoleEnum role) {
        this.role = role;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
}