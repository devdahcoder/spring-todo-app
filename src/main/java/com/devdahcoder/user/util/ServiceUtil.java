package com.devdahcoder.user.util;

import com.devdahcoder.user.contract.RoleEnum;
import com.devdahcoder.user.model.CreateUserModel;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class ServiceUtil {

    private final PasswordEncoder passwordEncoder;

    public ServiceUtil(PasswordEncoder passwordEncoder) {this.passwordEncoder = passwordEncoder;}


    private UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

    private void setDefaultUserRole(CreateUserModel user) {
        user.setRole(RoleEnum.USER);
    }

    private void encodeUserPassword(CreateUserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
