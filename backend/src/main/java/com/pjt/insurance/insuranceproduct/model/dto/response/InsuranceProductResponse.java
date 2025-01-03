package com.pjt.insurance.insuranceproduct.model.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceProductResponse {

    private Long id;               // 보험 상품 ID
    private String name;           // 보험 상품 이름
    private String description;    // 보험 상품 설명
    private String status;         // 보험 상품 상태 (예: 활성화, 만료 등)
    private String startDate;      // 보험 시작 날짜 (문자열 형식으로 변환)

    // 필요한 추가 필드가 있다면 여기에 추가할 수 있습니다.
}
