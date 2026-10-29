package com.backend.spring.JPA.service;

import com.backend.spring.JPA.entities.Users;
import com.backend.spring.JPA.exception.UserNotFoundException;
import com.backend.spring.JPA.model.CreateUserModel;
import com.backend.spring.JPA.model.UserModel;
import com.backend.spring.JPA.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserModel saveUser(CreateUserModel createUserModel) {
        Users user = new Users();
        user.setEmail(createUserModel.getEmail());
        user.setName(createUserModel.getName());

        Users savedUser = userRepository.save(user);
        return new UserModel(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public List<UserModel> getAllUsers() {
        List<Users> users = userRepository.findAll();

        List<UserModel> userList = new ArrayList<>();
        for (Users user : users) {
            UserModel userModel = new UserModel(user.getId(), user.getName(), user.getEmail());
            userList.add(userModel);
        }

        return userList;
    }


    public UserModel getUserById(Long id) {
        Users user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User not found with Id : " + id));
        return new UserModel(user.getId(), user.getName(), user.getEmail());
    }

    @Transactional
    public UserModel updateUserId(CreateUserModel updateUserModel, Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        user.setName(updateUserModel.getName());
        user.setEmail(updateUserModel.getEmail());

        // no save() required, dirty checking + @Transactional

        return new UserModel(user.getId(), user.getName(), user.getEmail());
    }

    public UserModel patchUserId(CreateUserModel patchUserModel, Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        if (patchUserModel.getEmail() != null) user.setEmail(patchUserModel.getEmail());
        if (patchUserModel.getName() != null) user.setName(patchUserModel.getName());

        return new UserModel(user.getId(), user.getName(), user.getEmail());
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserModel> getAllUsersPaginated(int page, int pageSize, String direction, String sortBy) {
        Sort sort;
        sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Users> usersPage = userRepository.findAll(pageable);

        List<UserModel> userList = new ArrayList<>();
        usersPage.forEach(user -> userList.add(new UserModel(user.getId(), user.getName(), user.getEmail())));

        return userList;
    }
}
