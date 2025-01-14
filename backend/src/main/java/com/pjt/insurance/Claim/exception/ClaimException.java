package com.pjt.insurance.Claim.exception;


import lombok.Getter;

@Getter
public class ClaimException extends RuntimeException {

    private final ClaimErrorCode claimErrorCode;

    public ClaimException(ClaimErrorCode claimErrorCode) {
        this.claimErrorCode = claimErrorCode;
    }
}
