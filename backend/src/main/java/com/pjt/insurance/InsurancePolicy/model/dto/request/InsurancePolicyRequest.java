package com.pjt.insurance.InsurancePolicy.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePolicyRequest {

    @NotNull(message = "가입자 ID는 필수입니다.") // 유효성 검사
    private Long memberId; // 가입자 ID

    @NotNull(message = "보험 상품 ID는 필수입니다.") // 유효성 검사
    private Long productId; // 보험 상품 ID

    @NotNull(message = "계약 시작일은 필수입니다.") // 유효성 검사
    private LocalDate startDate; // 계약 시작일

    @NotNull(message = "계약 만료일은 필수입니다.") // 유효성 검사
    private LocalDate endDate; // 계약 만료일

    // 추가적인 필드가 필요하다면 여기에 추가
}
