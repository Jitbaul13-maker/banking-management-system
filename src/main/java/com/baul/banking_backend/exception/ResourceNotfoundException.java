package com.baul.banking_backend.exception;

public class ResourceNotfoundException extends RuntimeException{
    public ResourceNotfoundException(String message) {
        super(message);
    }
}
