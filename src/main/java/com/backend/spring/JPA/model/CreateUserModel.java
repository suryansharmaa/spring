package com.backend.spring.JPA.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserModel {
    @NotBlank @NotNull @Size(max = 100)
    private String name;

    @NotBlank @NotNull @Email
    private String email;

    @NotNull
    private String password;
}
