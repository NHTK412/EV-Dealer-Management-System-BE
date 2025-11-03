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

import com.example.evsalesmanagement.dto.customer.CustomerRequestDTO;
import com.example.evsalesmanagement.dto.customer.CustomerResponseDTO;
import com.example.evsalesmanagement.service.CustomerService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    

   // Lấy danh sách tất cả khách hàng - có phân trang - sắp xếp
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CustomerResponseDTO>>> getAllCustomers(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {
    
        Sort sort = sortDir.equalsIgnoreCase("asc") 
            ? Sort.by(sortBy).ascending() 
            : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
    
        Page<CustomerResponseDTO> customerPage = customerService.getAllCustomers(pageable);
    
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get list customers successfully", customerPage)
        );
    }


    // Lấy thông tin chi tiết một khách hàng theo ID
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerById(@PathVariable Integer customerId) {
        CustomerResponseDTO customerDTO = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get customer information successfully", customerDTO)
        );
    }

    
    // Tạo khách hàng mới
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCustomer(
            @Valid @RequestBody CustomerRequestDTO customerRequestDTO,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                    error -> error.getField(),
                    error -> error.getDefaultMessage()
                ));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, "Invalid data", errors));
        }
        
        CustomerResponseDTO createdCustomer = customerService.createCustomer(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true, "Customer created successfully", createdCustomer));
    }

    
    // Cập nhật thông tin khách hàng
    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse<?>> updateCustomer(
            @PathVariable Integer customerId,
            @Valid @RequestBody CustomerRequestDTO customerRequestDTO,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                    error -> error.getField(),
                    error -> error.getDefaultMessage()
                ));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, "Invalid data", errors));
        }

        CustomerResponseDTO updatedCustomer = customerService.updateCustomer(customerId, customerRequestDTO);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Customer updated successfully", updatedCustomer)
        );
    }

    
    // Xóa khách hàng theo ID
    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Customer deleted successfully", null)
        );
    }

    
   // Lấy danh sách khách hàng theo cấp độ thành viên
    @GetMapping("/by-membership")
    public ResponseEntity<ApiResponse<Page<CustomerResponseDTO>>> getCustomersByMembership(
            @RequestParam String level,
            @RequestParam int page,
            @RequestParam int size) {
    
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerResponseDTO> customerPage = customerService.getCustomersByMembershipLevel(level, pageable);
    
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get customers by membership level successfully", customerPage)
        );
    }


    // Đếm số lượng khách hàng theo cấp độ thành viên
    @GetMapping("/membership/{level}/count")
    public ResponseEntity<ApiResponse<Long>> countCustomersByMembership(@PathVariable String level) {
        long count = customerService.countByMembershipLevel(level);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Count customers by membership level successfully", count)
        );
    }

    
    // Lấy tổng số khách hàng
    @GetMapping("/total-count")
    public ResponseEntity<ApiResponse<Long>> getTotalCustomers() {
        long total = customerService.getTotalCustomers();
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Get total customers successfully", total)
        );
    }
}