package com.devdahcoder.exception.handler;

import io.jsonwebtoken.JwtException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> getAuthenticationHandler(@NotNull JwtException authenticationException) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT authentication failed: " + authenticationException.getMessage());

    }

}
