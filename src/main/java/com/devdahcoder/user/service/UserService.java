package com.devdahcoder.user.service;

import com.devdahcoder.user.contract.UserServiceInterface;
import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public Iterable<UserMapperModel> findAllUsers() {

        return userRepository.findAllUsers();

    }

}
