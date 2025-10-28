package com.example.evsalesmanagement.dto;
import java.time.LocalDate;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.Vehicle;
public class TestDriveAppointmentDTO {
    private LocalDate dateOfAppointment;
    private LocalDate timeOfAppointment;
    private Customer customer;
    private Vehicle vehicle;
    private String status;

    public TestDriveAppointmentDTO() {}
 
    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public LocalDate getTimeOfAppointment() {
        return timeOfAppointment;
    }

    public void setTimeOfAppointment(LocalDate timeOfAppointment) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TestDriveAppointmentDTO [dateOfAppointment=" + dateOfAppointment + ", timeOfAppointment="
                + timeOfAppointment + ", customer=" + customer + ", vehicle=" + vehicle + ", status=" + status + "]";
    }

    
    
}
