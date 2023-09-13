package com.devdahcoder.user.service;

import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.user.contract.RoleEnum;
import com.devdahcoder.user.contract.UserServiceInterface;
import com.devdahcoder.user.model.AuthenticateUserModel;
import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;

    }

    @Override
    public List<UserMapperModel> findAllUsers(String order, int limit, int offset) {

        return userRepository.findAllUsers(order, limit, offset);

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
    public String authenticateUser(@NotNull AuthenticateUserModel authenticateUserModel) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticateUserModel.getUsername(), authenticateUserModel.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return null;

    }


    @Override
    public Optional<UserMapperModel> getUserById(UUID userId) {

        return userRepository.getUserById(userId);

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
