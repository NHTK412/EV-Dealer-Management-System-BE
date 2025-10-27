package com.example.evsalesmanagement.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//lichHenlaiThu = TestDriveAppointment
@Entity
@Table(name = "TestDriveAppointment")
public class TestDriveAppointment extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TestDriveAppointmentId")
    private Integer testDriveAppointmentId;

    //ngay hen = DateOfAppointment
    @Column(name = "DateOfAppointment")
    private LocalDate dateOfAppointment;

    @Column(name = "Status")
    private String status;

    //gio hen = TimeOfAppointment
    @Column(name = "TimeOfAppointment")
    private LocalTime timeOfAppointment;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "VehicleId")
    private Vehicle vehicle;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getTimeOfAppointment() {
        return timeOfAppointment;
    }

    public void setTimeOfAppointment(LocalTime timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

   
}