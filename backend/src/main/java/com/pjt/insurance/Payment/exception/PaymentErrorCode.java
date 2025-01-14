package com.pjt.insurance.Payment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PaymentErrorCode {
    NOT_EXISTS_PAYMENT("존재하지 않는 결제입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS_PAYMENT("이미 등록된 결제입니다.", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED("결제에 대한 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INVALID_PAYMENT_DETAILS("유효하지 않은 결제 세부정보입니다.", HttpStatus.BAD_REQUEST),
    PAYMENT_FAILED("결제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_EXPIRED("결제가 만료되었습니다.", HttpStatus.GONE);

    private final String message;
    private final HttpStatus httpStatus;
}
