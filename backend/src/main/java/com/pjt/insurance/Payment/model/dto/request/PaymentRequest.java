package com.pjt.insurance.Payment.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentRequest {

    @NotNull(message = "정책 ID는 필수입니다.")
    private Long policyId; // 결제와 관련된 정책 ID

    @NotNull(message = "결제 금액은 필수입니다.")
    @Positive(message = "결제 금액은 양수여야 합니다.")
    private Double amount; // 결제 금액

    @NotNull(message = "결제 상태는 필수입니다.")
    private String status; // 결제 상태 (예: "PENDING", "COMPLETED", "FAILED")
}
