package com.example.carsharingapp.exception;

public class EmptyDataException extends RuntimeException {
    public EmptyDataException(String message) {
        super(message);
    }
}
