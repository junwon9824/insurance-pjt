package com.pjt.insurance.InsurancePolicy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum InsurancePolicyErrorCode {
    NOT_EXISTS_POLICY("존재하지 않는 보험 상품입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS_POLICY("이미 등록된 보험 상품입니다.", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED("보험 상품에 대한 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INVALID_POLICY_DETAILS("유효하지 않은 보험 상품 세부정보입니다.", HttpStatus.BAD_REQUEST),
    POLICY_EXPIRED("보험 상품이 만료되었습니다.", HttpStatus.GONE);

    private final String message;
    private final HttpStatus httpStatus;

}
