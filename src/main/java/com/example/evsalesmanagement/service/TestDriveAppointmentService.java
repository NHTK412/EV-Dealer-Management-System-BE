package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentSummaryDTO;
import com.example.evsalesmanagement.enums.TestDriveAppointmentStatusEnum;
import com.example.evsalesmanagement.model.TestDriveAppointment;
import com.example.evsalesmanagement.repository.TestDriveAppointmentRepository;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestDriveAppointmentService {

    @Autowired
    private TestDriveAppointmentRepository repository;

    @Transactional
    public TestDriveAppointmentSummaryDTO modifyAppointment(Integer id, TestDriveAppointmentSummaryDTO request) {
        
        TestDriveAppointment existingAppointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn lái thử với id: " + id));

        // Cập nhật Ngày hẹn
        if (request.getDateOfAppointment() != null) {
            existingAppointment.setDateOfAppointment(request.getDateOfAppointment());
        }
        // Cập nhật Giờ hẹn
        if (request.getTimeOfAppointment() != null) {
            existingAppointment.setTimeOfAppointment(request.getTimeOfAppointment());
        }
        
        // Cập nhật Trạng thái
        if (request.getStatus() != null) {
            existingAppointment.setStatus(request.getStatus());
        }
     

        TestDriveAppointment updatedAppointment = repository.save(existingAppointment);
        return convertToDTO(updatedAppointment);
    }

    @Transactional
    public TestDriveAppointmentSummaryDTO updateAppointmentStatus(Integer id, String newStatus) {

        TestDriveAppointment existingAppointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn lái thử với id: " + id));
        
        TestDriveAppointmentStatusEnum statusEnum;
        try {
            statusEnum = TestDriveAppointmentStatusEnum.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Trạng thái mới không hợp lệ: " + newStatus);
        }

        if (existingAppointment.getStatus() == TestDriveAppointmentStatusEnum.ARRIVED && statusEnum == TestDriveAppointmentStatusEnum.CANCELLED) {
             throw new IllegalStateException("Không thể hủy lịch hẹn đã được đánh dấu là Đã Tới.");
        }

        existingAppointment.setStatus(statusEnum);
        
        TestDriveAppointment updatedAppointment = repository.save(existingAppointment);
        
        return convertToDTO(updatedAppointment);
    }

    @Transactional(readOnly = true)
    public List<TestDriveAppointmentSummaryDTO> getAllAppointments() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TestDriveAppointmentSummaryDTO> getAppointmentsByStatus(String status) {
        return repository.findByStatusIgnoreCase(status) 
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private TestDriveAppointmentSummaryDTO convertToDTO(TestDriveAppointment appointment) {
        TestDriveAppointmentSummaryDTO dto = new TestDriveAppointmentSummaryDTO();
        
        dto.setTestDriveAppointmentId(appointment.getTestDriveAppointmentId());
        dto.setDateOfAppointment(appointment.getDateOfAppointment());
        dto.setTimeOfAppointment(appointment.getTimeOfAppointment());
        dto.setStatus(appointment.getStatus());
        if (appointment.getCustomer() != null) {
        dto.setCustomerName(appointment.getCustomer().getCustomerName());
    }
        dto.setVehicleId(appointment.getVehicle().getVehicleId());
        
        
        return dto;
    }
}