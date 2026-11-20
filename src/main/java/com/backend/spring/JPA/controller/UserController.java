package com.backend.spring.JPA.controller;

import com.backend.spring.JPA.model.CreateUserModel;
import com.backend.spring.JPA.model.UserModel;
import com.backend.spring.JPA.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    // User -> own profile
    @GetMapping("/me")
    public ResponseEntity<UserModel> getMe() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getCurrentUser());
    }

    @PutMapping("/me")
    public ResponseEntity<UserModel> updateMe(@RequestBody CreateUserModel createUserModel) {
        return ResponseEntity.ok(userService.updateCurrentUser(createUserModel));
    }

    // Admin -> all users
    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Admin -> specific user
    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    //Admin -> delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

}
