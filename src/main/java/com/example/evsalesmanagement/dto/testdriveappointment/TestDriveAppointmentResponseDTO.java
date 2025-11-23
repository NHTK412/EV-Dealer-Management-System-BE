package com.example.evsalesmanagement.dto.testdriveappointment;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.evsalesmanagement.enums.TestDriveAppointmentStatusEnum;

public class TestDriveAppointmentResponseDTO {

    private Integer testDriveAppointmentId;
    private LocalDate dateOfAppointment;
    private LocalTime timeOfAppointment;
    private TestDriveAppointmentStatusEnum status;

    private Integer customerId;
    private String customerName;
    private String phoneNumber;
    private String email;

    private Integer vehicleId;
    private String chassicNumber;
    private String machineNumber;
    private String color;
    private String version;
    private String features;
    private String vehicleTypeName;
    private Integer manufactureYear;

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

    public TestDriveAppointmentStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TestDriveAppointmentStatusEnum status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getChassicNumber() {
        return chassicNumber;
    }

    public void setChassicNumber(String chassicNumber) {
        this.chassicNumber = chassicNumber;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

}
