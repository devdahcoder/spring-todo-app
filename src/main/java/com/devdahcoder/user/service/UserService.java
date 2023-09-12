package com.devdahcoder.user.service;

import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.user.contract.RoleEnum;
import com.devdahcoder.user.contract.UserServiceInterface;
import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Iterable<UserMapperModel> findAllUsers(String order, int limit) {

        return userRepository.findAllUsers(order, limit);

    }

    @Override
    public String createUser(@NotNull CreateUserModel user) {

        logger.info("Service: creating user: {}", user.getUsername());

        user.setRole(RoleEnum.USER);
        user.setUserId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        /**
         * Check if a user exists
         * Throws ApiAlreadyExistException if a user matching CreateUserModel is found
         * */
        if (this.userExists(user.getUsername())) {

            throw new ApiAlreadyExistException("User already exists");

        }

        //** Saves user to the database if user does not exist */
        return userRepository.createUser(user);

    }

    @Override
    public boolean userExists(String username) {

        return userRepository.userExists(username);

    }

    @Override
    public int countUser() {

        return userRepository.countUser();

    }


}
