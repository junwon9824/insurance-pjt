package com.pjt.insurance.InsurancePolicy.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePolicyResponse {

    private Long id; // 보험 계약 ID
    private Long memberId; // 가입자 ID
    private Long productId; // 보험 상품 ID
    private LocalDate startDate; // 계약 시작일
    private LocalDate endDate; // 계약 만료일
    private String status; // 계약 상태 (예: "ACTIVE", "EXPIRED", "CANCELLED")
    private LocalDate createdAt; // 생성일
    private LocalDate updatedAt; // 수정일

    // 추가적인 필드 및 메서드가 필요하다면 여기에 추가
}
