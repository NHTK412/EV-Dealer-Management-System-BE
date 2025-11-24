package com.example.evsalesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.customer.CustomerRequestDTO;
import com.example.evsalesmanagement.dto.customer.CustomerResponseDTO;
import com.example.evsalesmanagement.enums.CustomerMembershipLevelEnum;
import com.example.evsalesmanagement.exception.ConflictException;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Customer;
import com.example.evsalesmanagement.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Lấy tất cả khách hàng - có phân trang
    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(CustomerResponseDTO::new);
    }

    // Lấy khách hàng theo ID
    @Cacheable(value = "customer", key = "#customerId")
    public CustomerResponseDTO getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        return new CustomerResponseDTO(customer);
    }

    // Tạo khách hàng mới
    @Transactional
    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {
        if (customerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ConflictException("Email already exists: " + requestDTO.getEmail());
        }

        if (customerRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
            throw new ConflictException("Phone number already exists: " + requestDTO.getPhoneNumber());
        }

        Customer customer = new Customer();
        customer.setCustomerName(requestDTO.getCustomerName());
        customer.setGender(requestDTO.getGender());
        customer.setBirthDate(requestDTO.getBirthDate());
        customer.setPhoneNumber(requestDTO.getPhoneNumber());
        customer.setEmail(requestDTO.getEmail());
        customer.setAddress(requestDTO.getAddress());
        customer.setMembershipLevel(requestDTO.getMembershipLevel());

        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponseDTO(savedCustomer);
    }

    // Cập nhật khách hàng
    @CachePut(value = "customer", key = "#customerId")
    @Transactional
    public CustomerResponseDTO updateCustomer(Integer customerId, CustomerRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        if (customerRepository.existsByEmailAndCustomerIdNot(requestDTO.getEmail(), customerId)) {
            throw new ConflictException("Email already exists: " + requestDTO.getEmail());
        }

        if (customerRepository.existsByPhoneNumberAndCustomerIdNot(requestDTO.getPhoneNumber(), customerId)) {
            throw new ConflictException("Phone number already exists: " + requestDTO.getPhoneNumber());
        }

        customer.setCustomerName(requestDTO.getCustomerName());
        customer.setGender(requestDTO.getGender());
        customer.setBirthDate(requestDTO.getBirthDate());
        customer.setPhoneNumber(requestDTO.getPhoneNumber());
        customer.setEmail(requestDTO.getEmail());
        customer.setAddress(requestDTO.getAddress());
        customer.setMembershipLevel(requestDTO.getMembershipLevel());

        Customer updatedCustomer = customerRepository.save(customer);
        return new CustomerResponseDTO(updatedCustomer);
    }

    // Xóa khách hàng
    @CacheEvict(value = "customer", key = "#customerId")
    @Transactional
    public void deleteCustomer(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with id: " + customerId);
        }
        customerRepository.deleteById(customerId);
    }

    // Lấy khách hàng theo membership level - có phân trang
    public Page<CustomerResponseDTO> getCustomersByMembershipLevel(CustomerMembershipLevelEnum level, Pageable pageable) {
        return customerRepository.findByMembershipLevel(level, pageable)
                .map(CustomerResponseDTO::new);
    }

    // Đếm khách hàng theo membership level
   public long countByMembershipLevel(CustomerMembershipLevelEnum level) {
        return customerRepository.countByMembershipLevel(level);
    }


    // Tổng số khách hàng
    public long getTotalCustomers() {
        return customerRepository.count();
    }
}