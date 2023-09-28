package com.devdahcoder.security.configuration;

import com.devdahcoder.exception.authentication.JwtAuthenticationEntryPoint;
import com.devdahcoder.exception.handler.ApiExceptionHandler;
import com.devdahcoder.exception.handler.AuthenticationExceptionHandler;
import com.devdahcoder.security.filter.JwtAuthenticationFilter;
import com.devdahcoder.security.provider.UserNamePasswordAuthenticationProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserNamePasswordAuthenticationProvider userNamePasswordAuthenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AuthenticationExceptionHandler authenticationExceptionHandler;
    private final ApiExceptionHandler apiExceptionHandler;

    public SecurityConfiguration(UserNamePasswordAuthenticationProvider userNamePasswordAuthenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, AuthenticationExceptionHandler authenticationExceptionHandler, ApiExceptionHandler apiExceptionHandler) {

        this.userNamePasswordAuthenticationProvider = userNamePasswordAuthenticationProvider;

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;

        this.authenticationExceptionHandler = authenticationExceptionHandler;

        this.apiExceptionHandler = apiExceptionHandler;

    }

    @Autowired
    public void securityProviderBuilderConfig(@NotNull AuthenticationManagerBuilder authenticationManagerBuilder) {

        authenticationManagerBuilder.authenticationProvider(userNamePasswordAuthenticationProvider);

    }

    @Bean
    public AuthenticationManager authenticationManager(@NotNull AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain webSecurityFilter(@NotNull HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("api/v1/users/create", "api/v1/users/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
//                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
//						.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())))
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
//                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}
