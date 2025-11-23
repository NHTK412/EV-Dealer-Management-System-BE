package com.example.evsalesmanagement.service.order.calculator;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.Policy;
import com.example.evsalesmanagement.model.QuantityDiscountLevel;

@Component
public class QuantityDiscountCalculator implements DiscountCalculator {

    @Override
    public void calculateDiscount(Order order, Policy policy) {
        Integer totalQuantity = order.getOrderDetails().stream()
            .map(orderDetail -> orderDetail.getQuantity())
            .reduce(0, Integer::sum);

        List<QuantityDiscountLevel> quantityDiscountLevels = policy.getQuantityDiscountLevel();

        QuantityDiscountLevel discount = quantityDiscountLevels.stream()
            .filter(level -> totalQuantity >= level.getQuantityFrom() && totalQuantity <= level.getQuantityTo())
            .findAny()
            .orElse(null);

        BigDecimal discountPercentage = (discount != null) ? discount.getDiscountPercentage() : BigDecimal.ZERO;
        BigDecimal discountAmount = order.getOriginaAmount()
            .multiply(discountPercentage.divide(BigDecimal.valueOf(100)));

        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(order.getOriginaAmount().subtract(discountAmount));
    }
}