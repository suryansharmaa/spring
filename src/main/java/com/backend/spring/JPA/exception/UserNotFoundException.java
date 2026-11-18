package com.backend.spring.JPA.exception;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long userId) {
        super(ErrorCode.USER_NOT_FOUND, "User not found with id : "+userId);
    }
}
