package com.example.carsharingapp.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("User with email " + email + " not found.");
    }

    public UserNotFoundException(Long userId) {
        super("User with id " + userId + " not found.");
    }
}
