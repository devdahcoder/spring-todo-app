package com.devdahcoder.user.contract;

import com.devdahcoder.user.model.AuthenticateUserModel;
import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServiceInterface {

    public List<UserMapperModel> findAllUsers(String order, int limit, int offset);

    public String createUser(CreateUserModel user);

    public String authenticateUser(AuthenticateUserModel authenticateUserModel);

    public Optional<UserMapperModel> getUserById(UUID userId);

    public boolean userExists(String username);

    public int countUser();

}
