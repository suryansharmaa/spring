package com.backend.spring.RestAPI.repository;

import com.backend.spring.RestAPI.model.CreateUserModel;
import com.backend.spring.RestAPI.model.UserModel;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.ArrayList;


@Repository
public class UserRepository {
    List<UserModel> users = new ArrayList<>();

    public UserRepository() {
        users.add(new UserModel(UUID.randomUUID().toString(), "Peter", "peter@test.com"));
        users.add(new UserModel(UUID.randomUUID().toString(), "May", "auntmay@mail.com"));
        users.add(new UserModel(UUID.randomUUID().toString(), "Grey", "jeangrey@test.com"));
    }

    public List<UserModel> findAll() {
        return this.users;
    }

    public UserModel findUserById(String id) {
        for (UserModel user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
    }

    public @Nullable UserModel saveUser(CreateUserModel createUserModel) {
        UserModel user = new UserModel(UUID.randomUUID().toString(), createUserModel.getName(), createUserModel.getEmail());
        users.add(user);
        return user;
    }

    public @Nullable UserModel updateUser(CreateUserModel updateUserModel, String id) {
        for (UserModel user : users) {
            if (user.getId().equals(id)) {
                user.setName(updateUserModel.getName());
                user.setEmail(updateUserModel.getEmail());

                return user;
            }
        }
        return null;
    }

    public void removeUser(String id) {
        users.removeIf(userModel -> userModel.getId().equals(id));
    }
}
