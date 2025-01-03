package com.pjt.insurance.insuranceproduct.exception;


import lombok.Getter;

@Getter
public class InsuranceProductException extends RuntimeException{
    private final InsuranceProductErrorCode insuranceProductErrorCode;

    public UserException(UserErrorCode userErrorCode) {
        super(userErrorCode.getMessage());
        this.userErrorCode = userErrorCode;
    }
}
