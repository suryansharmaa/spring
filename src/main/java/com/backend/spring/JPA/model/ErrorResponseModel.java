package com.backend.spring.JPA.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseModel {
    private String code;
    private String errMessage;
}
