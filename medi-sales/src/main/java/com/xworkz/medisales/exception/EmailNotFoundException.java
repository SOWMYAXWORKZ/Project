package com.xworkz.medisales.exception;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String message){
        super(message);
    }
}
