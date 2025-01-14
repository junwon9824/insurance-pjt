package com.pjt.insurance.Claim.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ClaimErrorCode {
    NOT_EXISTS_CLAIM("존재하지 않는 청구입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS_CLAIM("이미 등록된 청구입니다.", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED("청구에 대한 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INVALID_CLAIM_DETAILS("유효하지 않은 청구 세부정보입니다.", HttpStatus.BAD_REQUEST),
    CLAIM_PROCESS_FAILED("청구 처리에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    CLAIM_EXPIRED("청구가 만료되었습니다.", HttpStatus.GONE);

    private final String message;
    private final HttpStatus httpStatus;
}
