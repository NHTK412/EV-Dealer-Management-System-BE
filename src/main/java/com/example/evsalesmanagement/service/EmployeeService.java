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
import com.example.evsalesmanagement.dto.employee.ResetPasswordDTO;
import com.example.evsalesmanagement.enums.EmployeeStatusEnum;
import com.example.evsalesmanagement.enums.RoleEnum;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.security.CustomerUserDetails;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    // Lấy tất cả nhân viên - có phân trang
    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        return employeeRepository.findByStatus(EmployeeStatusEnum.ACTIVE, pageable)
                .map(EmployeeResponseDTO::new);
    }

    // Lấy nhân viên theo ID
    // @Cacheable(value = "employee", key = "#employeeId")
    public EmployeeResponseDTO getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        return new EmployeeResponseDTO(employee);
    }

    // Tạo nhân viên mới
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        if (employeeRepository.existsByEmailAndStatus(requestDTO.getEmail(), EmployeeStatusEnum.ACTIVE)) {
            throw new ConflictException("Email already exists: " + requestDTO.getEmail());
        }

        if (employeeRepository.existsByPhoneNumberAndStatus(requestDTO.getPhoneNumber(), EmployeeStatusEnum.ACTIVE)) {
            throw new ConflictException("Phone number already exists: " + requestDTO.getPhoneNumber());
        }

        if (employeeRepository.existsByUsernameAndStatus(requestDTO.getUsername(), EmployeeStatusEnum.ACTIVE)) {
            throw new ConflictException("Username already exists: " + requestDTO.getUsername());
        }

        Employee employee = new Employee();
        employee.setEmployeeName(requestDTO.getEmployeeName());
        employee.setGender(requestDTO.getGender());
        employee.setBirthDate(requestDTO.getBirthDate());
        employee.setPhoneNumber(requestDTO.getPhoneNumber());
        employee.setEmail(requestDTO.getEmail());
        employee.setAddress(requestDTO.getAddress());
        employee.setRole(requestDTO.getRole());
        employee.setUsername(requestDTO.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Evm123@";
        String hashedPassword = encoder.encode(rawPassword);

        // System.out.println("Raw password: " + hashedPassword);

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
    // @CachePut(value = "employee", key = "#employeeId")
    @Transactional
    public EmployeeResponseDTO updateEmployee(Integer employeeId, EmployeeRequestDTO requestDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (employeeRepository.existsByEmailAndEmployeeIdNotAndStatus(
                requestDTO.getEmail(), employeeId, EmployeeStatusEnum.ACTIVE)) {
            throw new ConflictException("Email already exists: " + requestDTO.getEmail());
        }

        if (employeeRepository.existsByPhoneNumberAndEmployeeIdNotAndStatus(
                requestDTO.getPhoneNumber(), employeeId, EmployeeStatusEnum.ACTIVE)) {
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

    // @CacheEvict(value = "employee", key = "#employeeId")
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        employee.setStatus(EmployeeStatusEnum.INACTIVE);
        employeeRepository.save(employee);
    }

    public Page<EmployeeResponseDTO> getEmployeesByRole(RoleEnum role, Pageable pageable) {
        return employeeRepository.findByRoleAndStatus(role, EmployeeStatusEnum.ACTIVE, pageable)
                .map(EmployeeResponseDTO::new);
    }

    public Page<EmployeeResponseDTO> getEmployeesByAgency(Integer agencyId, Pageable pageable) {
        return employeeRepository.findByAgency_AgencyIdAndStatus(agencyId, EmployeeStatusEnum.ACTIVE, pageable)
                .map(EmployeeResponseDTO::new);
    }

    public long countByAgency(Integer agencyId) {
        return employeeRepository.countByAgency_AgencyIdAndStatus(agencyId, EmployeeStatusEnum.ACTIVE);
    }

    public long countByRole(RoleEnum role) {
        return employeeRepository.countByRoleAndStatus(role, EmployeeStatusEnum.ACTIVE);
    }

    @Transactional
    public void resetPassword(
            Integer employeeId,
            ResetPasswordDTO resetPasswordDTO) {
            
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(resetPasswordDTO.getOldPassword(), employee.getPassword())) {
            String hashedPassword = encoder.encode(resetPasswordDTO.getNewPassword());
            employee.setPassword(hashedPassword);
            employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException("Current password is incorrect");
        }

    }
}