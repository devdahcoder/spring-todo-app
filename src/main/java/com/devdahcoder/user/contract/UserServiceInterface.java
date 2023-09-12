package com.devdahcoder.user.contract;

import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceInterface {

    public Iterable<UserMapperModel> findAllUsers(String order, int limit, int offset);

    public String createUser(CreateUserModel user);

    public Optional<UserMapperModel> getUserById(UUID userId);

    public boolean userExists(String username);

    public int countUser();

}
