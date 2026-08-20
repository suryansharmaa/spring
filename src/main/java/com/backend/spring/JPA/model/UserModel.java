package com.backend.spring.JPA.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String name;
    private String email;
}
