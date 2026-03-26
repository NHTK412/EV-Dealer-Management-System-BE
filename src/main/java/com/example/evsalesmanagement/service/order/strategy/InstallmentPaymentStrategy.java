package com.example.evsalesmanagement.service.order.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evsalesmanagement.enums.PaymentStatusEnum;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.Order;
import com.example.evsalesmanagement.model.Payment;
import com.example.evsalesmanagement.model.PaymentPlan;
import com.example.evsalesmanagement.repository.PaymentPlanRepository;

@Component
public class InstallmentPaymentStrategy implements PaymentStrategy {

    // private final PaymentPlanRepository paymentPlanRepository;

    @Autowired
    private PaymentPlanRepository paymentPlanRepository;

    private Integer paymentPlanId;

    // public InstallmentPaymentStrategy(PaymentPlanRepository
    // paymentPlanRepository) {
    // this.paymentPlanRepository = paymentPlanRepository;
    // }

    public InstallmentPaymentStrategy withPaymentPlanId(Integer paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
        return this;
    }

    @Override
    public void applyPayment(Order order) {
        PaymentPlan paymentPlan = paymentPlanRepository.findById(paymentPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentPlan Not Found"));

        BigDecimal total = order.getTotalAmount();
        BigDecimal interestRate = paymentPlan.getInterestRate();
        BigDecimal downPercent = paymentPlan.getDownPaymentPercent();
        int numberOfInstallments = paymentPlan.getNumberOfInstallments();

        BigDecimal downPayment = calculateDownPayment(total, downPercent);
        createDownPayment(order, downPayment);

        BigDecimal loanAmount = total.subtract(downPayment);
        BigDecimal installmentAmount = calculateInstallmentAmount(loanAmount, interestRate, numberOfInstallments);

        createInstallmentPayments(order, installmentAmount, numberOfInstallments);
    }

    private BigDecimal calculateDownPayment(BigDecimal total, BigDecimal downPercent) {
        return total.multiply(downPercent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    private void createDownPayment(Order order, BigDecimal downPayment) {
        Payment payment = new Payment();
        payment.setNumberCycle(0);
        payment.setAmount(downPayment);
        payment.setDueDate(LocalDateTime.now());
        payment.setStatus(PaymentStatusEnum.UNPAID);
        payment.setPenaltyAmount(BigDecimal.ZERO);
        payment.setOrder(order);

        order.getPayments().add(payment);
    }

    private BigDecimal calculateInstallmentAmount(BigDecimal loanAmount, BigDecimal interestRate,
            int numberOfInstallments) {
        BigDecimal r = interestRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        BigDecimal onePlusR = r.add(BigDecimal.ONE);
        BigDecimal pow = onePlusR.pow(numberOfInstallments);
        BigDecimal numerator = loanAmount.multiply(r).multiply(pow);
        BigDecimal denominator = pow.subtract(BigDecimal.ONE);

        return numerator.divide(denominator, 2, RoundingMode.HALF_UP);
    }

    private void createInstallmentPayments(Order order, BigDecimal installmentAmount, int numberOfInstallments) {
        for (int i = 1; i <= numberOfInstallments; i++) {
            Payment payment = new Payment();
            payment.setNumberCycle(i);
            payment.setAmount(installmentAmount);
            payment.setDueDate(LocalDateTime.now().plusMonths(i));
            payment.setStatus(PaymentStatusEnum.UNPAID);
            payment.setPenaltyAmount(BigDecimal.ZERO);
            payment.setOrder(order);

            order.getPayments().add(payment);
        }
    }
}