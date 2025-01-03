package com.pjt.insurance.insuranceproduct.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceProductRequest {

    @NotBlank(message = "보험 상품 이름은 필수입니다.")
    @Size(max = 100, message = "보험 상품 이름은 100자 이내여야 합니다.")
    private String name; // 보험 상품 이름

    @Size(max = 255, message = "보험 상품 설명은 255자 이내여야 합니다.")
    private String description; // 보험 상품 설명

    @NotNull(message = "보험 시작 날짜는 필수입니다.")
    private LocalDate startDate; // 보험 시작 날짜

    @NotBlank(message = "보험 상태는 필수입니다.")
    @Size(max = 20, message = "보험 상태는 20자 이내여야 합니다.")
    private String status; // 보험 상태 (예: 활성화, 만료 등)

    // 필요한 추가 필드가 있다면 여기에 추가할 수 있습니다.
}
