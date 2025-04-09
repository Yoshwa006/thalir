package com.example.thalir.exception;

public class FileUrlAlreadyExistsException extends RuntimeException{

     public FileUrlAlreadyExistsException(String message){
        super(message);
    }
}
