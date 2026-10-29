package com.backend.spring.JPA.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errMessage) {
        super(errMessage);
    }
}
