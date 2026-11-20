package com.backend.spring.JPA.controller;

import com.backend.spring.JPA.model.CreateUserModel;
import com.backend.spring.JPA.model.LoginModel;
import com.backend.spring.JPA.model.LoginUserResponseModel;
import com.backend.spring.JPA.model.RegisterUserResponseModel;
import com.backend.spring.JPA.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseModel> registerUser(@RequestBody CreateUserModel createUserModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(createUserModel));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseModel> loginUser(@RequestBody LoginModel loginModel) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.loginUser(loginModel));
    }
}
