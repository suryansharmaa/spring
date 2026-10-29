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

    @PostMapping
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody CreateUserModel createUserModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(createUserModel));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/page")
    public ResponseEntity<List<UserModel>> getAllUsersPaginated(@RequestParam int page, @RequestParam int pageSize,
                                                                @RequestParam(defaultValue = "asc") String direction,
                                                                @RequestParam(defaultValue = "name") String sortBy) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersPaginated(page, pageSize, direction, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUserId(@RequestBody CreateUserModel updateUserModel, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserId(updateUserModel, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserModel> patchUserId(@RequestBody CreateUserModel patchUserModel, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.patchUserId(patchUserModel, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
