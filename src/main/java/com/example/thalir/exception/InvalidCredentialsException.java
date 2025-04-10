package com.example.thalir.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message){
        super(message);
    }
}
