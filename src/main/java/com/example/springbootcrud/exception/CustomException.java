package com.example.springbootcrud.exception;

public class CustomException extends RuntimeException {

    public CustomException() {}

    public CustomException(String message) {
        super(message);
    }
}
