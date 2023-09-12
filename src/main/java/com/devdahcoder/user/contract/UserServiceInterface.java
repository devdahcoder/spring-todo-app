package com.devdahcoder.user.contract;

import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;

public interface UserServiceInterface {

    public Iterable<UserMapperModel> findAllUsers(String order, int limit);

    public String createUser(CreateUserModel user);

    public boolean userExists(String username);

    public int countUser();

}
