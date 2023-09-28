package com.devdahcoder.exception.handler;

import com.devdahcoder.exception.api.ApiJwtExpiredException;
import io.jsonwebtoken.JwtException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AuthenticationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ApiJwtExpiredException.class })
    @ResponseBody
    public ResponseEntity<String> handleAuthenticationException(@NotNull RuntimeException ex) {
        // Create a custom error response and return it with the appropriate HTTP status code.
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT authentication failed: " + ex.getMessage());
    }

}
