package com.example.evsalesmanagement.service.order.factory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evsalesmanagement.dto.orderdetail.OrderDetailRequestDTO;
import com.example.evsalesmanagement.enums.OrderStatusEnum;
import com.example.evsalesmanagement.enums.OrderTypeEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Agency;
import com.example.evsalesmanagement.model.AgencyWholesalePrice;
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


    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VehicleTypeDetailRepository vehicleTypeDetailRepository;

    @Autowired
    private AgencyWholesalePriceRepository agencyWholesalePriceRepository;

    private Integer agencyId;
    private Integer employeeId;
    private String notes;
    private List<OrderDetailRequestDTO> orderDetailRequests;

    // AgencyRepository agencyRepository,
    // EmployeeRepository employeeRepository,
    // VehicleTypeDetailRepository vehicleTypeDetailRepository,
    // AgencyWholesalePriceRepository agencyWholesalePriceRepository) {
    // this.agencyRepository = agencyRepository;
    // this.employeeRepository = employeeRepository;
    // this.vehicleTypeDetailRepository = vehicleTypeDetailRepository;
    // this.agencyWholesalePriceRepository = agencyWholesalePriceRepository;

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
        order.setStatus(OrderStatusEnum.PENDING);
        order.setType(OrderTypeEnum.AGENCY);

        Map<Integer, Integer> vehicleTypeDetailMap = orderDetailRequests.stream()
                .collect(Collectors.toMap(
                        OrderDetailRequestDTO::getVehicleTypeDetailId,
                        OrderDetailRequestDTO::getQuantity));

        validateVehicleTypeDetails(vehicleTypeDetailMap);

        List<VehicleTypeDetail> vehicleTypeDetails = vehicleTypeDetailRepository
                .findAllById(vehicleTypeDetailMap.keySet());

        // Lấy thông tin giá sỉ kèm số lượng tối thiểu
        Map<Integer, AgencyWholesalePrice> wholesalePriceMap = getWholesalePriceWithMinQuantity(
                agency.getAgencyId(),
                vehicleTypeDetailMap.keySet());

        BigDecimal total = addOrderDetails(order, vehicleTypeDetails, vehicleTypeDetailMap, wholesalePriceMap);

        order.setOriginaAmount(total);
        order.setTotalAmount(total);

        return order;
    }

    private void validateVehicleTypeDetails(Map<Integer, Integer> vehicleTypeDetailMap) {
        if (vehicleTypeDetailMap.size() != orderDetailRequests.size()) {
            throw new ResourceNotFoundException("VehicleTypeDetail Not Found");
        }
    }

    private Map<Integer, AgencyWholesalePrice> getWholesalePriceWithMinQuantity(
            Integer agencyId,
            java.util.Set<Integer> vehicleTypeDetailIds) {

        List<AgencyWholesalePrice> wholesalePrices = agencyWholesalePriceRepository
                .findByAgency_AgencyIdAndVehicleTypeDetail_VehicleTypeDetailIdIn(agencyId, vehicleTypeDetailIds);

        return wholesalePrices.stream()
                .collect(Collectors.toMap(
                        awp -> awp.getVehicleTypeDetail().getVehicleTypeDetailId(),
                        awp -> awp));
    }

    private BigDecimal addOrderDetails(
            Order order,
            List<VehicleTypeDetail> vehicleTypeDetails,
            Map<Integer, Integer> vehicleTypeDetailMap,
            Map<Integer, AgencyWholesalePrice> wholesalePriceMap) {

        BigDecimal total = BigDecimal.ZERO;

        for (VehicleTypeDetail vehicleTypeDetail : vehicleTypeDetails) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setVehicleTypeDetail(vehicleTypeDetail);

            Integer quantity = vehicleTypeDetailMap.get(vehicleTypeDetail.getVehicleTypeDetailId());
            orderDetail.setQuantity(quantity);

            // Xác định giá: kiểm tra số lượng có đạt tối thiểu không
            BigDecimal price = determinePrice(vehicleTypeDetail, quantity, wholesalePriceMap);
            orderDetail.setPrice(price);
            orderDetail.setOrder(order);

            total = total.add(orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
            order.getOrderDetails().add(orderDetail);
        }

        return total;
    }

    /**
     * Xác định giá cho sản phẩm:
     * - Nếu có giá sỉ VÀ số lượng đạt tối thiểu → dùng giá sỉ
     * - Ngược lại → dùng giá thường
     */
    private BigDecimal determinePrice(
            VehicleTypeDetail vehicleTypeDetail,
            Integer orderQuantity,
            Map<Integer, AgencyWholesalePrice> wholesalePriceMap) {

        AgencyWholesalePrice wholesalePrice = wholesalePriceMap.get(vehicleTypeDetail.getVehicleTypeDetailId());

        if (wholesalePrice != null) {
            Integer minimumQuantity = wholesalePrice.getMinimumQuantity();

            // Kiểm tra số lượng đặt hàng có đạt số lượng tối thiểu không
            if (minimumQuantity != null && orderQuantity >= minimumQuantity) {
                // Đạt số lượng tối thiểu → dùng giá sỉ
                return wholesalePrice.getWholesalePrice();
            }
        }

        // Không đạt số lượng tối thiểu hoặc không có giá sỉ → dùng giá thường
        return vehicleTypeDetail.getPrice();
    }
}