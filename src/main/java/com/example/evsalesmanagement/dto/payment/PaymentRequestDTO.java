package com.example.evsalesmanagement.dto.payment;

import com.example.evsalesmanagement.enums.PaymentMethodEnum;
import com.example.evsalesmanagement.enums.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

public class PaymentRequestDTO {

    private PaymentMethodEnum paymentMethod;

    private PaymentTypeEnum paymentType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String vnpayCode;


    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeEnum paymentType) {
        this.paymentType = paymentType;
    }

    public String getVnpayCode() {
        return vnpayCode;
    }

    public void setVnpayCode(String vnpayCode) {
        this.vnpayCode = vnpayCode;
    }

    


    //     this.paymentId = paymentId;

}
