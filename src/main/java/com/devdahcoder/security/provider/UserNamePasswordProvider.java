package com.devdahcoder.security.provider;

import com.devdahcoder.security.configuration.PasswordConfiguration;
import com.devdahcoder.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserNamePasswordProvider implements AuthenticationProvider {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordConfiguration passwordConfiguration;

    public UserNamePasswordProvider(UserRepository userRepository, PasswordConfiguration passwordConfiguration) {

        this.userRepository = userRepository;
        this.passwordConfiguration = passwordConfiguration;

    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return null;

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);

    }

}
