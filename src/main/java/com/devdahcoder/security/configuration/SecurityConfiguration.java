package com.devdahcoder.security.configuration;

import com.devdahcoder.security.provider.UserNamePasswordAuthenticationProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserNamePasswordAuthenticationProvider userNamePasswordAuthenticationProvider;

    public SecurityConfiguration(UserNamePasswordAuthenticationProvider userNamePasswordAuthenticationProvider) {

        this.userNamePasswordAuthenticationProvider = userNamePasswordAuthenticationProvider;

    }

    @Autowired
    public void securityProviderBuilderConfig(@NotNull AuthenticationManagerBuilder authenticationManagerBuilder) {

        authenticationManagerBuilder.authenticationProvider(userNamePasswordAuthenticationProvider);

    }

    @Bean
    public SecurityFilterChain webSecurityFilter(@NotNull HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
//                                .requestMatchers("api/v1/users/**")
//                                .authenticated()
                                .anyRequest()
                                .permitAll()
                )
                .build();

    }

}
