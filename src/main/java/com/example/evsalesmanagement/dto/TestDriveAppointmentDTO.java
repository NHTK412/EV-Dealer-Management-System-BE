package com.example.evsalesmanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestDriveAppointmentDTO {
    
    private Integer testDriveAppointmentId;
    
    private LocalDate dateOfAppointment;
    
    private LocalTime timeOfAppointment;
    
    private String status;
    
    private String customerName; 
    
    private String vehicleName;

    public TestDriveAppointmentDTO() {
    }

    public Integer getTestDriveAppointmentId() {
        return testDriveAppointmentId;
    }

    public void setTestDriveAppointmentId(Integer testDriveAppointmentId) {
        this.testDriveAppointmentId = testDriveAppointmentId;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public LocalTime getTimeOfAppointment() {
        return timeOfAppointment;
    }

    public void setTimeOfAppointment(LocalTime timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
