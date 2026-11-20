package com.backend.spring.JPA.service;

import com.backend.spring.JPA.entities.Role;
import com.backend.spring.JPA.entities.Users;
import com.backend.spring.JPA.model.CreateUserModel;
import com.backend.spring.JPA.model.LoginModel;
import com.backend.spring.JPA.model.LoginUserResponseModel;
import com.backend.spring.JPA.model.RegisterUserResponseModel;
import com.backend.spring.JPA.repository.UserRepository;
import com.backend.spring.JPA.security.JwtService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RegisterUserResponseModel registerUser(CreateUserModel createUserModel) {
        Users user = new Users();
        user.setEmail(createUserModel.getEmail());
        user.setName(createUserModel.getName());
        user.setPassword(passwordEncoder.encode(createUserModel.getPassword()));
        user.setRole(Role.USER);

        Users savedUser = userRepository.save(user);

        return new RegisterUserResponseModel(savedUser.getName(), savedUser.getId());
    }

    public LoginUserResponseModel loginUser(LoginModel loginModel) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword())
        );

        String jwtToken = jwtService.generateJwtToken((UserDetails) Objects.requireNonNull(authentication.getPrincipal()));
        return new LoginUserResponseModel(jwtToken);
    }
}
