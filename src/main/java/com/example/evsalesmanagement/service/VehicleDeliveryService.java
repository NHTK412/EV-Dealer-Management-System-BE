package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryRequestDTO;
import com.example.evsalesmanagement.dto.vehicledelivery.VehicleDeliveryResponseDTO;
import com.example.evsalesmanagement.enums.VehicleDeliveryStatusEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.VehicleDelivery;
import com.example.evsalesmanagement.repository.VehicleDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.repository.OrderRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import java.util.List;

@Service
public class VehicleDeliveryService {

    @Autowired
    private VehicleDeliveryRepository repository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

  //Tạo đơn hàng giao xe mới
    @Transactional
    public VehicleDeliveryResponseDTO createVehicleDelivery(VehicleDeliveryRequestDTO request) {
    
        //Tìm Order và kiểm tra tồn tại
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng với id: " + request.getOrderId()));

        //Định nghĩa trạng thái hoạt động
        List<VehicleDeliveryStatusEnum> activeStatuses = List.of(
            VehicleDeliveryStatusEnum.PREPARING,
            VehicleDeliveryStatusEnum.DELIVERING
        );
        
        //Kiểm tra logic
        if (repository.existsByOrderIdAndStatusIn(order, activeStatuses)) {
            throw new IllegalStateException("Đơn hàng đã có giao xe đang hoạt động. Không thể tạo giao xe mới cho đơn hàng này.");
        }
        
        //Tìm Employee
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên với id: " + request.getEmployeeId()));

        // Khởi tạo
        VehicleDelivery newVehicleDelivery = new VehicleDelivery(); 

        //Map dữ liệu
        newVehicleDelivery.setOderId(order);
        newVehicleDelivery.setEmployee(employee);
        newVehicleDelivery.setExpectedDeliveryDate(
            request.getExpectedDeliveryDate().atStartOfDay()
        );

        // Set trạng thái ban đầu
        VehicleDeliveryStatusEnum initDeliveryStatusEnum = request.getStatus() != null 
                                                        ? request.getStatus() 
                                                        : VehicleDeliveryStatusEnum.PREPARING;
        newVehicleDelivery.setStatus(initDeliveryStatusEnum);

        //Lưu vào DB
        VehicleDelivery savedDelivery = repository.save(newVehicleDelivery);
        
        return new VehicleDeliveryResponseDTO(savedDelivery);
    }

    /**
     * Lấy tất cả giao xe.
     */
    public List<VehicleDeliveryResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(VehicleDeliveryResponseDTO::new)
                .toList();
    }

    public List<VehicleDeliveryResponseDTO> getDeliveriesByStatus(VehicleDeliveryStatusEnum status) {
        return repository.findByStatus(status)
                .stream()
                .map(VehicleDeliveryResponseDTO::new)
                .toList();
    }
    @Transactional
    public VehicleDeliveryResponseDTO updateVehicleDelivery(Integer id, VehicleDeliveryRequestDTO request) {

        // Tìm id
        VehicleDelivery existingDelivery = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch giao xe với id: " + id));
        
        // cập nhật ngày hẹn lại
        if (request.getExpectedDeliveryDate() != null) {
            existingDelivery.setExpectedDeliveryDate(request.getExpectedDeliveryDate().atStartOfDay());
        }

        if (request.getStatus() != null) {
            existingDelivery.setStatus(request.getStatus());
        }
        
        //Thay nhân viên giao xe
        if (request.getEmployeeId() != null && 
            (existingDelivery.getEmployee() == null || !request.getEmployeeId().equals(existingDelivery.getEmployee().getEmployeeId()))) {
            
            Employee employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên mới với id: " + request.getEmployeeId()));
            existingDelivery.setEmployee(employee);
        }
        
        VehicleDelivery updatedDelivery = repository.save(existingDelivery);
        
        return new VehicleDeliveryResponseDTO(updatedDelivery);
    }
}