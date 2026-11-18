package com.backend.spring.JPA.exception;

public class EmailAlreadyExistsException extends ConflictException {

    public EmailAlreadyExistsException() {
        super(ErrorCode.EMAIL_ALREADY_EXISTS, "Email already exists");
    }
}
