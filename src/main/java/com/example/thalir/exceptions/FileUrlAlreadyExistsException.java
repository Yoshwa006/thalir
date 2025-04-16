package com.example.thalir.exceptions;

public class FileUrlAlreadyExistsException extends RuntimeException{

     public FileUrlAlreadyExistsException(String message){
        super(message);
    }
}
