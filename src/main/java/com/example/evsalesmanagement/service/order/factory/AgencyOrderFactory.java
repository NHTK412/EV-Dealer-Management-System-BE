package com.example.evsalesmanagement.service.order.factory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.evsalesmanagement.dto.orderdetail.OrderDetailRequestDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.enums.OrderTypeEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.Employee;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.OrderDetail;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.repository.AgencyRepository;
import com.example.evsalesmanagement.repository.AgencyWholesalePriceRepository;
import com.example.evsalesmanagement.repository.EmployeeRepository;
import com.example.evsalesmanagement.repository.VehicleTypeDetailRepository;

@Component
public class AgencyOrderFactory implements OrderFactory {
    
    private final AgencyRepository agencyRepository;
    private final EmployeeRepository employeeRepository;
    private final VehicleTypeDetailRepository vehicleTypeDetailRepository;
    private final AgencyWholesalePriceRepository agencyWholesalePriceRepository;
    
    private Integer agencyId;
    private Integer employeeId;
    private String notes;
    private List<OrderDetailRequestDTO> orderDetailRequests;

    public AgencyOrderFactory(
            AgencyRepository agencyRepository,
            EmployeeRepository employeeRepository,
            VehicleTypeDetailRepository vehicleTypeDetailRepository,
            AgencyWholesalePriceRepository agencyWholesalePriceRepository) {
        this.agencyRepository = agencyRepository;
        this.employeeRepository = employeeRepository;
        this.vehicleTypeDetailRepository = vehicleTypeDetailRepository;
        this.agencyWholesalePriceRepository = agencyWholesalePriceRepository;
    }

    public AgencyOrderFactory withAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
        return this;
    }

    public AgencyOrderFactory withEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public AgencyOrderFactory withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public AgencyOrderFactory withOrderDetails(List<OrderDetailRequestDTO> orderDetailRequests) {
        this.orderDetailRequests = orderDetailRequests;
        return this;
    }

    @Override
    public Order createOrder() {
        Agency agency = agencyRepository.findById(agencyId)
            .orElseThrow(() -> new ResourceNotFoundException("Agency Not Found"));

        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));

        Order order = new Order();
        order.setNotes(notes);
        order.setAgency(employee.getAgency());
        order.setDealderAgency(agency);
        order.setEmployee(employee);
        order.setStatus(OrderStatusEnum.DELIVERED);
        order.setType(OrderTypeEnum.AGENCY);

        Map<Integer, Integer> vehicleTypeDetailMap = orderDetailRequests.stream()
            .collect(Collectors.toMap(
                OrderDetailRequestDTO::getVehicleTypeDetailId,
                OrderDetailRequestDTO::getQuantity
            ));

        validateVehicleTypeDetails(vehicleTypeDetailMap);

        List<VehicleTypeDetail> vehicleTypeDetails = vehicleTypeDetailRepository
            .findAllById(vehicleTypeDetailMap.keySet());

        Map<Integer, BigDecimal> vehicleTypeDetailPriceMap = getWholesalePrices(agency.getAgencyId(), vehicleTypeDetailMap.keySet());

        BigDecimal total = addOrderDetails(order, vehicleTypeDetails, vehicleTypeDetailMap, vehicleTypeDetailPriceMap);

        order.setOriginaAmount(total);
        order.setTotalAmount(total);

        return order;
    }

    private void validateVehicleTypeDetails(Map<Integer, Integer> vehicleTypeDetailMap) {
        if (vehicleTypeDetailMap.size() != orderDetailRequests.size()) {
            throw new ResourceNotFoundException("VehicleTypeDetail Not Found");
        }
    }

    private Map<Integer, BigDecimal> getWholesalePrices(Integer agencyId, java.util.Set<Integer> vehicleTypeDetailIds) {
        Map<Integer, BigDecimal> priceMap = agencyWholesalePriceRepository
            .findByAgency_AgencyIdAndVehicleTypeDetail_VehicleTypeDetailIdIn(agencyId, vehicleTypeDetailIds)
            .stream()
            .collect(Collectors.toMap(
                awp -> awp.getVehicleTypeDetail().getVehicleTypeDetailId(),
                awp -> awp.getWholesalePrice()
            ));

        if (priceMap.size() != orderDetailRequests.size()) {
            throw new ResourceNotFoundException("VehicleTypeDetail Not Found");
        }

        return priceMap;
    }

    private BigDecimal addOrderDetails(
            Order order,
            List<VehicleTypeDetail> vehicleTypeDetails,
            Map<Integer, Integer> vehicleTypeDetailMap,
            Map<Integer, BigDecimal> vehicleTypeDetailPriceMap) {
        
        BigDecimal total = BigDecimal.ZERO;

        for (VehicleTypeDetail vehicleTypeDetail : vehicleTypeDetails) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setVehicleTypeDetail(vehicleTypeDetail);
            orderDetail.setQuantity(vehicleTypeDetailMap.get(vehicleTypeDetail.getVehicleTypeDetailId()));
            orderDetail.setPrice(vehicleTypeDetailPriceMap.get(vehicleTypeDetail.getVehicleTypeDetailId()));
            orderDetail.setOrder(order);

            total = total.add(orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
            order.getOrderDetails().add(orderDetail);
        }

        return total;
    }
}