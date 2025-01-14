package com.pjt.insurance.Claim.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClaimResponse {

    private Long id; // 결제 ID
    private Long policyId; // 결제와 관련된 정책 ID
    private Double amount; // 결제 금액
    private String status; // 결제 상태
    private String createdAt; // 생성일 (문자열 형식으로 변환)
    private String updatedAt; // 수정일 (문자열 형식으로 변환)
}
