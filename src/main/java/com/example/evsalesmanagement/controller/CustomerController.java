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
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.evsalesmanagement.enums.CustomerMembershipLevelEnum;
import com.example.evsalesmanagement.service.CustomerService;
import com.example.evsalesmanagement.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

        @Autowired
        private CustomerService customerService;

        // <<<<<<< HEAD
        // Lấy danh sách tất cả khách hàng - có phân trang - sắp xếp
        // @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF')")
        @GetMapping
        public ResponseEntity<ApiResponse<Page<CustomerResponseDTO>>> getAllCustomers(
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(required = false) String sortBy,
                        @RequestParam(required = false) String sortDir) {
                // =======
                // // Lấy tất cả khách hàng - có phân trang - có sort
                // @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF',
                // 'DEALER_STAFF','ADMIN')")
                // @GetMapping
                // public ResponseEntity<ApiResponse<Page<CustomerResponseDTO>>>
                // getAllCustomers(
                // @RequestParam int page,
                // @RequestParam int size,
                // @RequestParam(required = false) String sortBy,
                // @RequestParam(required = false) String sortDir) {
                // >>>>>>> 29c2e7207eb5f529d717230bf9417c008d876d44

                Pageable pageable;

                // <<<<<<< HEAD
                // Nếu không truyền sortBy hoặc sortDir → bỏ sorting
                if (sortBy == null || sortBy.isEmpty() || sortDir == null || sortDir.isEmpty()) {
                        pageable = PageRequest.of(page - 1, size);
                } else {
                        Sort sort = sortDir.equalsIgnoreCase("asc")
                                        ? Sort.by(sortBy).ascending()
                                        : Sort.by(sortBy).descending();

                        pageable = PageRequest.of(page - 1, size, sort);
                }

                Page<CustomerResponseDTO> customerPage = customerService.getAllCustomers(pageable);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get list customers successfully", customerPage));
                // =======
                // if (sortBy == null || sortDir == null || sortBy.isEmpty() ||
                // sortDir.isEmpty()) {
                // pageable = PageRequest.of(page, size);
                // } else {
                // Sort sort = sortDir.equalsIgnoreCase("asc")
                // ? Sort.by(sortBy).ascending()
                // : Sort.by(sortBy).descending();

                // pageable = PageRequest.of(page, size, sort);
                // >>>>>>> 29c2e7207eb5f529d717230bf9417c008d876d44
                // }

                // Page<CustomerResponseDTO> customerPage =
                // customerService.getAllCustomers(pageable);

                // return ResponseEntity.ok(
                // new ApiResponse<>(true, "Get list customers successfully", customerPage));
        }

        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @GetMapping("/{customerId}")
        public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerById(@PathVariable Integer customerId) {
                CustomerResponseDTO customerDTO = customerService.getCustomerById(customerId);
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get customer information successfully", customerDTO));
        }

        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @PostMapping
        public ResponseEntity<ApiResponse<?>> createCustomer(
                        @Valid @RequestBody CustomerRequestDTO customerRequestDTO,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                                        .collect(Collectors.toMap(
                                                        error -> error.getField(),
                                                        error -> error.getDefaultMessage()));
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(new ApiResponse<>(false, "Invalid data", errors));
                }

                CustomerResponseDTO createdCustomer = customerService.createCustomer(customerRequestDTO);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ApiResponse<>(true, "Customer created successfully", createdCustomer));
        }

        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @PutMapping("/{customerId}")
        public ResponseEntity<ApiResponse<?>> updateCustomer(
                        @PathVariable Integer customerId,
                        @Valid @RequestBody CustomerRequestDTO customerRequestDTO,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                                        .collect(Collectors.toMap(
                                                        error -> error.getField(),
                                                        error -> error.getDefaultMessage()));
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(new ApiResponse<>(false, "Invalid data", errors));
                }

                CustomerResponseDTO updatedCustomer = customerService.updateCustomer(customerId, customerRequestDTO);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Customer updated successfully", updatedCustomer));
        }

        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @DeleteMapping("/{customerId}")
        public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Integer customerId) {
                customerService.deleteCustomer(customerId);
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Customer deleted successfully", null));
        }

        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @GetMapping("/by-membership")
        public ResponseEntity<ApiResponse<Page<CustomerResponseDTO>>> getCustomersByMembership(
                        @RequestParam String level,
                        @RequestParam int page,
                        @RequestParam int size) {

                CustomerMembershipLevelEnum enumLevel;

                try {
                        enumLevel = CustomerMembershipLevelEnum.valueOf(level.toUpperCase());
                } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().body(
                                        new ApiResponse<>(false, "Invalid membership level: " + level, null));
                }

                Pageable pageable = PageRequest.of(page, size);
                Page<CustomerResponseDTO> customerPage = customerService.getCustomersByMembershipLevel(enumLevel,
                                pageable);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get customers by membership level successfully",
                                                customerPage));
        }

        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @GetMapping("/membership/{level}/count")
        public ResponseEntity<ApiResponse<Long>> countCustomersByMembership(@PathVariable String level) {

                CustomerMembershipLevelEnum enumLevel;

                try {
                        enumLevel = CustomerMembershipLevelEnum.valueOf(level.toUpperCase());
                } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().body(
                                        new ApiResponse<>(false, "Invalid membership level: " + level, null));
                }

                long count = customerService.countByMembershipLevel(enumLevel);

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Count customers by membership level successfully", count));
        }

        @PreAuthorize("hasAnyRole('DEALER_MANAGER', 'EVM_STAFF', 'DEALER_STAFF','ADMIN')")
        @GetMapping("/total-count")
        public ResponseEntity<ApiResponse<Long>> getTotalCustomers() {
                long total = customerService.getTotalCustomers();
                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Get total customers successfully", total));
        }
}
