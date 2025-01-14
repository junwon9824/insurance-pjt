package com.pjt.insurance.Payment.exception;


import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException {

    private final PaymentErrorCode paymentErrorCode;

    public PaymentException(PaymentErrorCode paymentErrorCode) {
        this.paymentErrorCode = paymentErrorCode;
    }
}
