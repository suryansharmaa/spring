package com.backend.spring.RestAPI.controller;

import com.backend.spring.RestAPI.model.CreateUserModel;
import com.backend.spring.RestAPI.model.UserModel;
import com.backend.spring.RestAPI.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody CreateUserModel createUserModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody CreateUserModel updateUserModel, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(updateUserModel, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
