package com.backend.spring.RestAPI.service;

import com.backend.spring.RestAPI.model.CreateUserModel;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import com.backend.spring.RestAPI.model.UserModel;
import com.backend.spring.RestAPI.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAllUsers() {
        return this.userRepository.findAll();
    }

    public UserModel getUserById(String id) {
        return userRepository.findUserById(id);
    }

    public @Nullable UserModel createUser(CreateUserModel createUserModel) {
        return userRepository.saveUser(createUserModel);
    }

    public @Nullable UserModel updateUser(CreateUserModel updateUserModel, String id) {
/*
        *** Commented because updateUserModel already returns null if userId is not found ***
        if (userRepository.findUserById(id) == null) {
            return null;
        }
*/
        return userRepository.updateUser(updateUserModel, id);
    }

    public void deleteUserById(String id) {
        userRepository.removeUser(id);
    }
}
