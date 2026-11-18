package com.backend.spring.JPA.exception;

import lombok.Getter;

public abstract class ResourceNotFoundException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    protected ResourceNotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
