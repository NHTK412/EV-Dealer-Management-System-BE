
package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.employee.EmployeeRequestDTO;
import com.example.evsalesmanagement.dto.employee.EmployeeResponseDTO;
import com.example.evsalesmanagement.enums.EmployeeStatusEnum;
import com.example.evsalesmanagement.enums.RoleEnum;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    // Lấy tất cả nhân viên - có phân trang
    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(EmployeeResponseDTO::new);
    }

    // Lấy nhân viên theo ID
    @Cacheable(value = "employee", key = "#employeeId")
    public EmployeeResponseDTO getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        return new EmployeeResponseDTO(employee);
    }

    // Tạo nhân viên mới
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        if (employeeRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ConflictException("Email already exists: " + requestDTO.getEmail());
        }

        if (employeeRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
            throw new ConflictException("Phone number already exists: " + requestDTO.getPhoneNumber());
        }

        Employee employee = new Employee();
        employee.setEmployeeName(requestDTO.getEmployeeName());
        employee.setGender(requestDTO.getGender());
        employee.setBirthDate(requestDTO.getBirthDate());
        employee.setPhoneNumber(requestDTO.getPhoneNumber());
        employee.setEmail(requestDTO.getEmail());
        employee.setAddress(requestDTO.getAddress());
        employee.setRole(requestDTO.getRole());

        employee.setUsername(requestDTO.getEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "Evm123@";
        String hashedPassword = encoder.encode(rawPassword);

        employee.setPassword(hashedPassword);

        employee.setStatus(EmployeeStatusEnum.ACTIVE);

        if (requestDTO.getAgencyId() != null) {
            Agency agency = agencyRepository.findById(requestDTO.getAgencyId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Agency not found with id: " + requestDTO.getAgencyId()));
            employee.setAgency(agency);
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeResponseDTO(savedEmployee);
    }

    // Cập nhật nhân viên
    @CachePut(value = "employee", key = "#employeeId")
    @Transactional
    public EmployeeResponseDTO updateEmployee(Integer employeeId, EmployeeRequestDTO requestDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (employeeRepository.existsByEmailAndEmployeeIdNot(requestDTO.getEmail(), employeeId)) {
            throw new ConflictException("Email already exists: " + requestDTO.getEmail());
        }

        if (employeeRepository.existsByPhoneNumberAndEmployeeIdNot(requestDTO.getPhoneNumber(), employeeId)) {
            throw new ConflictException("Phone number already exists: " + requestDTO.getPhoneNumber());
        }

        employee.setEmployeeName(requestDTO.getEmployeeName());
        employee.setGender(requestDTO.getGender());
        employee.setBirthDate(requestDTO.getBirthDate());
        employee.setPhoneNumber(requestDTO.getPhoneNumber());
        employee.setEmail(requestDTO.getEmail());
        employee.setAddress(requestDTO.getAddress());
        employee.setRole(requestDTO.getRole());

        if (requestDTO.getAgencyId() != null) {
            Agency agency = agencyRepository.findById(requestDTO.getAgencyId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Agency not found with id: " + requestDTO.getAgencyId()));
            employee.setAgency(agency);
        } else {
            employee.setAgency(null);
        }

        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeResponseDTO(updatedEmployee);
    }

    // Xóa nhân viên
    @CacheEvict(value = "employee", key = "#employeeId")
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }

    // Lấy nhân viên theo position - có phân trang
    // <<<<<<< HEAD
    public Page<EmployeeResponseDTO> getEmployeesByRole(RoleEnum role, Pageable pageable) {
        return employeeRepository.findByRole(role, pageable)
                // =======
                // public Page<EmployeeResponseDTO> getEmployeesByPosition(String position,
                // Pageable pageable) {
                // return employeeRepository.findByPosition(position, pageable)
                // >>>>>>> feat/Khang/cauHinhRedis
                .map(EmployeeResponseDTO::new);
    }

    // Lấy nhân viên theo agency - có phân trang
    public Page<EmployeeResponseDTO> getEmployeesByAgency(Integer agencyId, Pageable pageable) {
        return employeeRepository.findByAgency_AgencyId(agencyId, pageable)
                .map(EmployeeResponseDTO::new);
    }

    // Đếm nhân viên theo agency
    public long countByAgency(Integer agencyId) {
        return employeeRepository.countByAgency_AgencyId(agencyId);
    }

    // Đếm nhân viên theo position
    public long countByRole(RoleEnum role) {
        return employeeRepository.countByRole(role);
    }
}