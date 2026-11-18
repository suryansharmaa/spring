package com.backend.spring.JPA.model;

import com.backend.spring.JPA.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseModel {
    private ErrorCode code;
    private int status;
}
