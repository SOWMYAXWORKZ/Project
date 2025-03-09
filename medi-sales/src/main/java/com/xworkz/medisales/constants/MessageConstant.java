package com.xworkz.medisales.constants;

import lombok.Getter;

@Getter
public enum MessageConstant {
    OTP_VALIDATE_SUCCESS_MESSAGE("OTP Validation Success"),
    OTP_VALIDATE_UN_SUCCESS_MESSAGE("OTP Validation Failed");

    private String message;

    MessageConstant(String message){
        this.message = message;
    }
}