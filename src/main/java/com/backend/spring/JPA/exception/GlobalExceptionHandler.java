package com.backend.spring.JPA.exception;

import com.backend.spring.JPA.model.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseModel(
                "USER_NOT_FOUND", e.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashMap<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        StringBuilder errMessage = new StringBuilder();
        boolean isFirst = true;
        for (String field : fieldErrors.keySet()) {
            if (!isFirst) errMessage.append(", ");
            isFirst = false;
            errMessage.append(field).append(" : ").append(fieldErrors.get(field));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseModel(
                "INVALID_INPUT", errMessage.toString()
        ));
    }
}
