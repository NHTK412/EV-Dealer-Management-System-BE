package com.example.evsalesmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentDTO;
import com.example.evsalesmanagement.service.TestDriveAppointmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/test-drive-appointments")
public class TestDriveAppointmentController {

    @Autowired
    private TestDriveAppointmentService testDriveAppointmentService;

    @GetMapping()
    public ResponseEntity<List<TestDriveAppointmentDTO>> getAllAppointments() {
        List<TestDriveAppointmentDTO> appointments = testDriveAppointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<TestDriveAppointmentDTO>> getPendingAppointments() {
        List<TestDriveAppointmentDTO> appointments = testDriveAppointmentService.getPendingAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/scheduled")
    public ResponseEntity<List<TestDriveAppointmentDTO>> getScheduledAppointments() {
        List<TestDriveAppointmentDTO> appointments = testDriveAppointmentService.getScheduledAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<TestDriveAppointmentDTO>> getCompletedAppointments() {
        List<TestDriveAppointmentDTO> appointments = testDriveAppointmentService.getCompletedAppointments();
        return ResponseEntity.ok(appointments);
    }
}