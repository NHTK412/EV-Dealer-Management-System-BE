package com.example.evsalesmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evsalesmanagement.dto.salesreport.AgencySalesDTO;
import com.example.evsalesmanagement.dto.salesreport.EmployeeSalesDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.MonthlySales;
import com.example.evsalesmanagement.repository.MonthlySalesRepository;
import com.example.evsalesmanagement.repository.OrderRepository;

@Service
public class SalesReportService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MonthlySalesRepository monthlySalesRepository;

    /**
     * Lấy doanh số theo tất cả nhân viên bán hàng trong tháng
     */
    @Transactional(readOnly = true)
    public List<EmployeeSalesDTO> getEmployeeSalesByMonth(Integer year, Integer month) {
        List<EmployeeSalesDTO> salesList = orderRepository.findEmployeeSalesByMonth(year, month);
        salesList.forEach(sales -> {
            sales.setYear(year);
            sales.setMonth(month);
        });
        return salesList;
    }

    /**
     * Lấy doanh số theo tất cả nhân viên bán hàng trong năm
     */
    @Transactional(readOnly = true)
    public List<EmployeeSalesDTO> getEmployeeSalesByYear(Integer year) {
        List<EmployeeSalesDTO> salesList = orderRepository.findEmployeeSalesByYear(year);
        salesList.forEach(sales -> sales.setYear(year));
        return salesList;
    }

    /**
     * Lấy doanh số của một nhân viên bán hàng cụ thể theo tháng
     */
    @Transactional(readOnly = true)
    public EmployeeSalesDTO getEmployeeSalesByEmployeeAndMonth(Integer employeeId, Integer year, Integer month) {
        EmployeeSalesDTO sales = orderRepository.findEmployeeSalesByEmployeeAndMonth(employeeId, year, month);
        if (sales == null) {
            throw new ResourceNotFoundException(
                    "Không tìm thấy dữ liệu doanh số cho nhân viên ID: " + employeeId + " trong tháng " + month + "/"
                            + year);
        }
        sales.setYear(year);
        sales.setMonth(month);
        return sales;
    }

    /**
     * Lấy doanh số của một nhân viên bán hàng cụ thể theo năm
     */
    @Transactional(readOnly = true)
    public EmployeeSalesDTO getEmployeeSalesByEmployeeAndYear(Integer employeeId, Integer year) {
        EmployeeSalesDTO sales = orderRepository.findEmployeeSalesByEmployeeAndYear(employeeId, year);
        if (sales == null) {
            throw new ResourceNotFoundException(
                    "Không tìm thấy dữ liệu doanh số cho nhân viên ID: " + employeeId + " trong năm " + year);
        }
        sales.setYear(year);
        return sales;
    }

    /**
     * Lấy doanh số theo tất cả đại lý trong tháng (từ bảng MonthlySales)
     */
    @Transactional(readOnly = true)
    public List<AgencySalesDTO> getAgencySalesByMonth(Integer year, Integer month) {
        List<MonthlySales> monthlySalesList = monthlySalesRepository.findAllByMonthAndYear(month, year);
        return monthlySalesList.stream()
                .map(AgencySalesDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Lấy doanh số của một đại lý cụ thể theo tháng
     */
    @Transactional(readOnly = true)
    public AgencySalesDTO getAgencySalesByAgencyAndMonth(Integer agencyId, Integer year, Integer month) {
        MonthlySales monthlySales = monthlySalesRepository.findByAgencyAndMonthAndYear(agencyId, month, year)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy dữ liệu doanh số cho đại lý ID: " + agencyId + " trong tháng " + month + "/"
                                + year));
        return new AgencySalesDTO(monthlySales);
    }

    /**
     * Lấy doanh số của một đại lý cụ thể theo năm
     */
    @Transactional(readOnly = true)
    public List<AgencySalesDTO> getAgencySalesByAgencyAndYear(Integer agencyId, Integer year) {
        List<MonthlySales> monthlySalesList = monthlySalesRepository.findByAgencyAndYear(agencyId, year);
        if (monthlySalesList.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Không tìm thấy dữ liệu doanh số cho đại lý ID: " + agencyId + " trong năm " + year);
        }
        return monthlySalesList.stream()
                .map(AgencySalesDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Lấy doanh số tất cả đại lý theo năm
     */
    @Transactional(readOnly = true)
    public List<AgencySalesDTO> getAgencySalesByYear(Integer year) {
        List<MonthlySales> monthlySalesList = monthlySalesRepository.findAllByYear(year);
        return monthlySalesList.stream()
                .map(AgencySalesDTO::new)
                .collect(Collectors.toList());
    }

}
