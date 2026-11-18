package com.backend.spring.JPA.exception;

import com.backend.spring.JPA.model.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponseModel> build(ErrorCode errorCode, HttpStatus status) {
        ErrorResponseModel body = new ErrorResponseModel(
                errorCode, status.value()
        );

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleResourceNotFoundException(ResourceNotFoundException e) {
        return build(e.getErrorCode(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseModel> handleConflictException(ConflictException e) {
        return build(e.getErrorCode(), HttpStatus.CONFLICT);
    }
}
