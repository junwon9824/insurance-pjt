package com.pjt.insurance.InsurancePolicy.exception;


import lombok.Getter;

@Getter
public class InsuranceProductException extends RuntimeException{
    private final InsuranceProductErrorCode insuranceProductErrorCode;



    public InsuranceProductException(InsuranceProductErrorCode insuranceProductErrorCode) {
        this.insuranceProductErrorCode = insuranceProductErrorCode;
    }
}
