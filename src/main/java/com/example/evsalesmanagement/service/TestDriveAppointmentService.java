package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentRequestDTO;
import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentResponseDTO;
import com.example.evsalesmanagement.dto.testdriveappointment.TestDriveAppointmentSummaryDTO;
import com.example.evsalesmanagement.enums.TestDriveAppointmentStatusEnum;
import com.example.evsalesmanagement.exception.BadRequestException;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.model.TestDriveAppointment;
import com.example.evsalesmanagement.model.Vehicle;
import com.example.evsalesmanagement.repository.CustomerRepository;
import com.example.evsalesmanagement.repository.TestDriveAppointmentRepository;
import com.example.evsalesmanagement.repository.VehicleRepository;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;

// import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestDriveAppointmentService {

    @Autowired
    private TestDriveAppointmentRepository testDriveAppointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public TestDriveAppointmentSummaryDTO createAppointment(TestDriveAppointmentRequestDTO request) {
        // Validate Customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy khách hàng với id: " + request.getCustomerId()));

        // Validate Vehicle
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy xe với id: " + request.getVehicleId()));

        // Validate date and time
        if (request.getDateOfAppointment() == null) {
            throw new BadRequestException("Ngày hẹn không được để trống");
        }
        if (request.getTimeOfAppointment() == null) {
            throw new BadRequestException("Giờ hẹn không được để trống");
        }

        // Create new appointment
        TestDriveAppointment appointment = new TestDriveAppointment();
        appointment.setDateOfAppointment(request.getDateOfAppointment());
        appointment.setTimeOfAppointment(request.getTimeOfAppointment());
        appointment.setStatus(TestDriveAppointmentStatusEnum.SCHEDULED);
        appointment.setCustomer(customer);
        appointment.setVehicle(vehicle);

        TestDriveAppointment savedAppointment = testDriveAppointmentRepository.save(appointment);
        return convertToDTO(savedAppointment);
    }

    @Transactional
    public TestDriveAppointmentSummaryDTO modifyAppointment(Integer id, TestDriveAppointmentSummaryDTO request) {

        TestDriveAppointment existingAppointment = testDriveAppointmentRepository.findById(id)
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

        TestDriveAppointment updatedAppointment = testDriveAppointmentRepository.save(existingAppointment);
        return convertToDTO(updatedAppointment);
    }

    @Transactional
    public TestDriveAppointmentSummaryDTO updateAppointment(Integer id, TestDriveAppointmentRequestDTO request) {
        TestDriveAppointment existingAppointment = testDriveAppointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn lái thử với id: " + id));

        // Update Customer if provided
        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy khách hàng với id: " + request.getCustomerId()));
            existingAppointment.setCustomer(customer);
        }

        // Update Vehicle if provided
        if (request.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy xe với id: " + request.getVehicleId()));
            existingAppointment.setVehicle(vehicle);
        }

        // Update date and time
        if (request.getDateOfAppointment() != null) {
            existingAppointment.setDateOfAppointment(request.getDateOfAppointment());
        }
        if (request.getTimeOfAppointment() != null) {
            existingAppointment.setTimeOfAppointment(request.getTimeOfAppointment());
        }

        TestDriveAppointment updatedAppointment = testDriveAppointmentRepository.save(existingAppointment);
        return convertToDTO(updatedAppointment);
    }

    @Transactional
    public TestDriveAppointmentSummaryDTO updateAppointmentStatus(Integer id, String newStatus) {

        TestDriveAppointment existingAppointment = testDriveAppointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn lái thử với id: " + id));

        TestDriveAppointmentStatusEnum statusEnum;
        try {
            statusEnum = TestDriveAppointmentStatusEnum.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Trạng thái mới không hợp lệ: " + newStatus);
        }

        if (existingAppointment.getStatus() == TestDriveAppointmentStatusEnum.ARRIVED
                && statusEnum == TestDriveAppointmentStatusEnum.CANCELLED) {
            throw new IllegalStateException("Không thể hủy lịch hẹn đã được đánh dấu là Đã Tới.");
        }

        existingAppointment.setStatus(statusEnum);

        TestDriveAppointment updatedAppointment = testDriveAppointmentRepository.save(existingAppointment);

        return convertToDTO(updatedAppointment);
    }

    @Transactional
    public void deleteAppointment(Integer id) {
        TestDriveAppointment appointment = testDriveAppointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn lái thử với id: " + id));

        // Only allow deletion if status is PENDING or CANCELLED
        if (appointment.getStatus() == TestDriveAppointmentStatusEnum.ARRIVED
                || appointment.getStatus() == TestDriveAppointmentStatusEnum.SCHEDULED) {
            throw new BadRequestException(
                    "Không thể xóa lịch hẹn có trạng thái " + appointment.getStatus()
                            + ". Chỉ có thể xóa lịch hẹn PENDING hoặc CANCELLED.");
        }

        testDriveAppointmentRepository.delete(appointment);
    }

    @Transactional(readOnly = true)
    public List<TestDriveAppointmentSummaryDTO> getAllAppointments(Pageable pageable, String status) {

        Page<TestDriveAppointment> appointmentPage = (status == null || status.isEmpty())
                ? testDriveAppointmentRepository.findAll(pageable)
                : testDriveAppointmentRepository.findByStatusIgnoreCase(status, pageable);

        return appointmentPage
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // @Transactional(readOnly = true)
    // public List<TestDriveAppointmentSummaryDTO> getAppointmentsByStatus(String
    // status) {
    // return repository.findByStatusIgnoreCase(status)
    // .stream()
    // .map(this::convertToDTO)
    // .collect(Collectors.toList());
    // }

    // public TestDriveRespon

    public TestDriveAppointmentResponseDTO getAppointmentById(Integer id) {
        TestDriveAppointment appointment = testDriveAppointmentRepository.findByIdWithCustomer(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch hẹn lái thử với id:"));

        TestDriveAppointmentResponseDTO testDriveAppointmentResponseDTO = new TestDriveAppointmentResponseDTO();

        testDriveAppointmentResponseDTO.setTestDriveAppointmentId(appointment.getTestDriveAppointmentId());
        testDriveAppointmentResponseDTO.setDateOfAppointment(appointment.getDateOfAppointment());
        testDriveAppointmentResponseDTO.setTimeOfAppointment(appointment.getTimeOfAppointment());
        testDriveAppointmentResponseDTO.setStatus(appointment.getStatus());

        if (appointment.getCustomer() != null) {
            testDriveAppointmentResponseDTO.setCustomerId(appointment.getCustomer().getCustomerId());
            testDriveAppointmentResponseDTO.setCustomerName(appointment.getCustomer().getCustomerName());
            testDriveAppointmentResponseDTO.setEmail(appointment.getCustomer().getEmail());
            testDriveAppointmentResponseDTO.setPhoneNumber(appointment.getCustomer().getPhoneNumber());
        }
        if (appointment.getVehicle() != null) {
            testDriveAppointmentResponseDTO.setVehicleId(appointment.getVehicle().getVehicleId());
            testDriveAppointmentResponseDTO.setChassicNumber(appointment.getVehicle().getChassicNumber());
            testDriveAppointmentResponseDTO.setMachineNumber(appointment.getVehicle().getMachineNumber());
            testDriveAppointmentResponseDTO.setColor(appointment.getVehicle().getVehicleTypeDetail().getColor());
            testDriveAppointmentResponseDTO.setVersion(appointment.getVehicle().getVehicleTypeDetail().getVersion());
            testDriveAppointmentResponseDTO.setFeatures(appointment.getVehicle().getVehicleTypeDetail().getFeatures());
            testDriveAppointmentResponseDTO.setVehicleTypeName(
                    appointment.getVehicle().getVehicleTypeDetail().getVehicleType().getVehicleTypeName());
            testDriveAppointmentResponseDTO.setManufactureYear(
                    appointment.getVehicle().getVehicleTypeDetail().getVehicleType().getManufactureYear());
        }

        return testDriveAppointmentResponseDTO;
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