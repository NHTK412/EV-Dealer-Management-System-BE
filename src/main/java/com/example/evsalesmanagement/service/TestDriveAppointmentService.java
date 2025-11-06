package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.TestDriveAppointmentDTO;
import com.example.evsalesmanagement.model.TestDriveAppointment;
import com.example.evsalesmanagement.repository.TestDriveAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestDriveAppointmentService {

    @Autowired
    private TestDriveAppointmentRepository repository;

    //Lấy tất cả lịch hẹn lái thử
    public List<TestDriveAppointmentDTO> getAllAppointments() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Lấy danh sách lịch hẹn trạng thái Pending
    public List<TestDriveAppointmentDTO> getPendingAppointments() {
        return repository.findPendingAppointments()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Lấy danh sách lịch hẹn trạng thái Scheduled
    public List<TestDriveAppointmentDTO> getScheduledAppointments() {
        return repository.findScheduledAppointments()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Lấy danh sách lịch hẹn trạng thái Completed
    public List<TestDriveAppointmentDTO> getCompletedAppointments() {
        return repository.findCompletedAppointments()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TestDriveAppointmentDTO convertToDTO(TestDriveAppointment appointment) {
        TestDriveAppointmentDTO dto = new TestDriveAppointmentDTO();

        dto.setTestDriveAppointmentId(appointment.getTestDriveAppointmentId());
        dto.setDateOfAppointment(appointment.getDateOfAppointment());
        dto.setTimeOfAppointment(appointment.getTimeOfAppointment());
        dto.setStatus(appointment.getStatus());

        //Lấy tên khách hàng
        if (appointment.getCustomer() != null) {
            dto.setCustomerName(appointment.getCustomer().getCustomerName());
        } else {
            dto.setCustomerName("Không xác định");
        }

        //Lấy tên xe
        if (appointment.getVehicle() != null 
            && appointment.getVehicle().getVehicleTypeDetail() != null 
            && appointment.getVehicle().getVehicleTypeDetail().getVehicleType() != null) {
            dto.setVehicleName(appointment.getVehicle().getVehicleTypeDetail().getVehicleType().getVehicleTypeName());
        } else {
            dto.setVehicleName("Không xác định");
        }

        return dto;
    }
}