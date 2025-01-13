package com.pjt.insurance.InsurancePolicy.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class InsurancePolicyException extends RuntimeException {
    private final InsurancePolicyErrorCode insurancePolicyErrorCode;

    public InsurancePolicyException(InsurancePolicyErrorCode insurancePolicyErrorCode) {
        this.insurancePolicyErrorCode = insurancePolicyErrorCode;
    }

}
