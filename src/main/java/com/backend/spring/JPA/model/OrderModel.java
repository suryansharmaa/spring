package com.backend.spring.JPA.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderModel {
    private Long id;
    private String productName;
    private UserModel user;
}
