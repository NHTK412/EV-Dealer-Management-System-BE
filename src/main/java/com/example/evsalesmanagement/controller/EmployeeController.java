package com.example.evsalesmanagement.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evsalesmanagement.dto.employee.EmployeeRequestDTO;
import com.example.evsalesmanagement.dto.employee.EmployeeResponseDTO;
import com.example.evsalesmanagement.service.EmployeeService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;

        // Lấy danh sách tất cả nhân viên - có phân trang - sắp xếp
        @GetMapping
        public ResponseEntity<ApiResponse<Page<EmployeeResponseDTO>>> getAllEmployees(
                        @RequestParam int page,
                        @RequestParam int size,
                        @RequestParam String sortBy,
                        @RequestParam String sortDir) {

                Sort sort = sortDir.equalsIgnoreCase("asc")
                                ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();
                Pageable pageable = PageRequest.of(page, size, sort);

                Page<EmployeeResponseDTO> employeePage = employeeService.getAllEmployees(pageable);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get list employees successfully", employeePage));
        }

        // Lấy thông tin chi tiết một nhân viên theo ID
        @GetMapping("/{employeeId}")
        public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeById(@PathVariable Integer employeeId) {
                EmployeeResponseDTO employeeDTO = employeeService.getEmployeeById(employeeId);
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get employee information successfully", employeeDTO));
        }

        // Tạo nhân viên mới
        @PostMapping
        public ResponseEntity<ApiResponse<?>> createEmployee(
                        @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                                        .collect(Collectors.toMap(
                                                        error -> error.getField(),
                                                        error -> error.getDefaultMessage()));
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(new ApiResponse<>(false, "Invalid data", errors));
                }

                EmployeeResponseDTO createdEmployee = employeeService.createEmployee(employeeRequestDTO);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ApiResponse<>(true, "Employee created successfully", createdEmployee));
        }

        // Cập nhật thông tin nhân viên
        @PutMapping("/{employeeId}")
        public ResponseEntity<ApiResponse<?>> updateEmployee(
                        @PathVariable Integer employeeId,
                        @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                                        .collect(Collectors.toMap(
                                                        error -> error.getField(),
                                                        error -> error.getDefaultMessage()));
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(new ApiResponse<>(false, "Invalid data", errors));
                }

                EmployeeResponseDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeRequestDTO);
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Employee updated successfully", updatedEmployee));
        }

        // Xóa nhân viên theo ID
        @DeleteMapping("/{employeeId}")
        public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Integer employeeId) {
                employeeService.deleteEmployee(employeeId);
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Employee deleted successfully", null));
        }

        // Lấy danh sách nhân viên theo chức vụ - phân trang
        @GetMapping("/by-position")
        public ResponseEntity<ApiResponse<Page<EmployeeResponseDTO>>> getEmployeesByPosition(
                        @RequestParam String position,
                        @RequestParam int page,
                        @RequestParam int size) {

                Pageable pageable = PageRequest.of(page, size);
                Page<EmployeeResponseDTO> employeePage = employeeService.getEmployeesByPosition(position, pageable);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get employees by position successfully", employeePage));
        }

        // Lấy danh sách nhân viên theo đại lý - phân trang
        @GetMapping("/agencies/{agencyId}")
        public ResponseEntity<ApiResponse<Page<EmployeeResponseDTO>>> getEmployeesByAgency(
                        @PathVariable Integer agencyId,
                        @RequestParam int page,
                        @RequestParam int size) {

                Pageable pageable = PageRequest.of(page, size);
                Page<EmployeeResponseDTO> employeePage = employeeService.getEmployeesByAgency(agencyId, pageable);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get employees by agency successfully", employeePage));
        }

        // Đếm số lượng nhân viên theo đại lý
        @GetMapping("/agencies/{agencyId}/count")
        public ResponseEntity<ApiResponse<Long>> countEmployeesByAgency(@PathVariable Integer agencyId) {
                long count = employeeService.countByAgency(agencyId);
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Count employees by agency successfully", count));
        }

        // Đếm số lượng nhân viên theo chức vụ
        @GetMapping("/positions/{position}/count")
        public ResponseEntity<ApiResponse<Long>> countEmployeesByPosition(@PathVariable String position) {
                long count = employeeService.countByPosition(position);
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Count employees by position successfully", count));
        }
}