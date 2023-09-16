package com.devdahcoder.security.provider;

import com.devdahcoder.user.model.UserDetailModel;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserNamePasswordAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();

        String password = authentication.getCredentials().toString();

        UserDetailModel user = userRepository.loadUserByUsername(username);

        return this.verifyAuthentication(user, username, password);

    }

    public Authentication verifyAuthentication(UserDetailModel user, String username, String password) {

        if (verifyPassword(user, password) && verifyUsername(user, username)) {

            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

        } else {

            throw new BadCredentialsException("Invalid username or password");

        }

    }

    public boolean verifyUsername(@NotNull UserDetailModel user, @NotNull String username) {

        return username.equals(user.getUsername());

    }

    public boolean verifyPassword(@NotNull UserDetailModel user, String password) {

        return passwordEncoder.matches(password, user.getPassword());

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);

    }

}
