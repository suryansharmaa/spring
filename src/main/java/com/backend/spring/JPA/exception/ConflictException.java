package com.backend.spring.JPA.exception;

import lombok.Getter;

public abstract class ConflictException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    protected ConflictException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
