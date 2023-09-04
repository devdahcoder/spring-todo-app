package com.devdahcoder.user.contract;

import com.devdahcoder.user.model.UserMapperModel;

import java.util.Iterator;

public interface UserServiceInterface {

    public Iterable<UserMapperModel> findAllUsers();


}
