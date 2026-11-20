package com.backend.spring.JPA.service;

import com.backend.spring.JPA.entities.Users;
import com.backend.spring.JPA.exception.UserNotFoundException;
import com.backend.spring.JPA.model.CreateUserModel;
import com.backend.spring.JPA.model.UserModel;
import com.backend.spring.JPA.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private UserModel map(Users user) {
        return new UserModel(user.getId(), user.getName(), user.getEmail());
    }

    private Users getLoggedInUser() {
        String email = Objects.requireNonNull(SecurityContextHolder.getContext()
                .getAuthentication()).getName();

        return userRepository.findByEmail(email).orElseThrow();
    }

    public UserModel getCurrentUser() {
        Users user = getLoggedInUser();
        return map(user);
    }

    public List<UserModel> getAllUsers() {
        List<Users> users = userRepository.findAll();

        List<UserModel> userList = new ArrayList<>();
        for (Users user : users) {
            UserModel userModel = map(user);
            userList.add(userModel);
        }

        return userList;
    }


    public UserModel getUserById(Long userId) {
        Users user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(userId));
        return map(user);
    }


    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserModel updateCurrentUser(CreateUserModel createUserModel) {
        Users user = getLoggedInUser();

        user.setName(createUserModel.getName());
        user.setEmail(createUserModel.getEmail());

        return map(user);
    }
}
