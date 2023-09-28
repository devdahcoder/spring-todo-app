package com.devdahcoder.exception.authentication;

import com.devdahcoder.exception.api.ApiJwtExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {this.handlerExceptionResolver = handlerExceptionResolver;}

    @Override
    public void commence(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull AuthenticationException authException)
            throws IOException, ServletException {

        logger.info("The type of error you're having is this ", authException);

//        response.addHeader("WWW-Authenticate", "Basic realm=\"Realm\"");
//        this.handlerExceptionResolver.resolveException(request, response, null, authException);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
//        response.getWriter().write("{\"status\": \"" + HttpStatus.UNAUTHORIZED + "\", \"error\": \"JWT Token Error\", \"message\": \"" + authException.getMessage() + "\"}");

//        if (authException instanceof ApiJwtExpiredException) {
//
//            response.addHeader("WWW-Authenticate", "Basic realm=\"Realm\"");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"status\": \"" + HttpStatus.UNAUTHORIZED + "\", \"error\": \"JWT Token Error\", \"message\": \"" + authException.getMessage() + "\"}");
//
//
//        }

//        if (authException instanceof RuntimeException) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token has expired");
//        } else if (exception instanceof JwtSignatureException) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token has an invalid signature");
//        } else if (exception instanceof JwtMalformedException) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is malformed");
//        } else {
//            // Handle other authentication exceptions
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
//        }

    }

}