package com.example.evsalesmanagement.service.order.calculator;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.evsalesmanagement.model.MonthlySales;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.Policy;
import com.example.evsalesmanagement.repository.MonthlySalesRepository;

@Component
public class SalesDiscountCalculator implements DiscountCalculator {

    private final MonthlySalesRepository monthlySalesRepository;

    public SalesDiscountCalculator(MonthlySalesRepository monthlySalesRepository) {
        this.monthlySalesRepository = monthlySalesRepository;
    }

    @Override
    public void calculateDiscount(Order order, Policy policy) {
        LocalDate previousMonth = LocalDate.now().minusMonths(1);
        int month = previousMonth.getMonthValue();
        int year = previousMonth.getYear();

        MonthlySales monthlySales = monthlySalesRepository
            .findByAgencyAndMonthAndYear(order.getDealderAgency().getAgencyId(), month, year)
            .orElse(null);

        BigDecimal discountPercentage = (monthlySales != null) ? monthlySales.getSalesAmount() : BigDecimal.ZERO;
        BigDecimal discountAmount = order.getOriginaAmount()
            .multiply(discountPercentage.divide(BigDecimal.valueOf(100)));

        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(order.getOriginaAmount().subtract(discountAmount));
    }
}