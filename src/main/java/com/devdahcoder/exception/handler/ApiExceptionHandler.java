package com.devdahcoder.exception.handler;

import com.devdahcoder.exception.api.*;
import com.devdahcoder.exception.contract.JwtExceptionType;
import com.devdahcoder.exception.model.ApiAuthenticationExceptionModel;
import com.devdahcoder.response.error.BadRequestApiResponseError;
import com.devdahcoder.response.error.BadRequestFieldApiResponseError;
import com.devdahcoder.exception.model.ApiExceptionModel;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final HttpServletRequest httpServletRequest;
    private final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    public ApiExceptionHandler(HttpServletRequest httpServletRequest) {

        this.httpServletRequest = httpServletRequest;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestApiResponseError<BadRequestFieldApiResponseError>> getRequestHandler(@NotNull MethodArgumentNotValidException methodArgumentNotValidException) {

        BadRequestApiResponseError<BadRequestFieldApiResponseError> badRequestApiResponseError = new BadRequestApiResponseError<>(
                HttpStatus.BAD_REQUEST,
                "Invalid input",
                HttpStatus.BAD_REQUEST.value(),
                methodArgumentNotValidException
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(requestError -> new BadRequestFieldApiResponseError(requestError.getField(), requestError.getDefaultMessage()))
                        .toList()
        );

        return new ResponseEntity<>(badRequestApiResponseError, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ApiAlreadyExistException.class)
    public ResponseEntity<ApiExceptionModel> getAlreadyExistHandler(@NotNull ApiAlreadyExistException apiAlreadyExistException) {

        ApiExceptionModel apiExceptionModel = new ApiExceptionModel(
                HttpStatus.CONFLICT.value(),
                apiAlreadyExistException.getMessage(),
                apiAlreadyExistException.getCause(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiExceptionModel, HttpStatus.CONFLICT);

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ApiNotFoundException.class)
    public ResponseEntity<ApiExceptionModel> getNotFoundHandler(@NotNull ApiNotFoundException apiNotFoundException) {

        ApiExceptionModel apiExceptionModel = new ApiExceptionModel(
                HttpStatus.CONFLICT.value(),
                apiNotFoundException.getMessage(),
                apiNotFoundException.getCause(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiExceptionModel, HttpStatus.NOT_FOUND);

    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ApiBadCredentialException.class)
    public ResponseEntity<ApiAuthenticationExceptionModel> getBadCredentialHandler(@NotNull ApiBadCredentialException apiBadCredentialException) {

        ApiAuthenticationExceptionModel apiAuthenticationExceptionModel = new ApiAuthenticationExceptionModel(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED,
                "Authentication failed",
                apiBadCredentialException.getMessage(),
                httpServletRequest.getRequestURL().toString(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiAuthenticationExceptionModel, HttpStatus.UNAUTHORIZED);

    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ApiJwtExpiredException.class)
    public ResponseEntity<ApiAuthenticationExceptionModel> getExpiredJwtExceptionHandler(@NotNull ApiJwtExpiredException apiJwtExpiredException) {

        logger.error("Jwt token has expired user should re-authenticate handler", apiJwtExpiredException);

        ApiAuthenticationExceptionModel apiAuthenticationExceptionModel = new ApiAuthenticationExceptionModel(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED,
                "Authentication failed",
                apiJwtExpiredException.getMessage(),
                httpServletRequest.getRequestURL().toString(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiAuthenticationExceptionModel, HttpStatus.UNAUTHORIZED);

    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ApiAccessDeniedException.class)
    public ResponseEntity<ApiAuthenticationExceptionModel> getAccessDeniedException(@NotNull ApiAccessDeniedException apiAccessDeniedException) {

        ApiAuthenticationExceptionModel apiAuthenticationExceptionModel = new ApiAuthenticationExceptionModel(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED,
                "Access Denied",
                apiAccessDeniedException.getMessage(),
                httpServletRequest.getRequestURL().toString(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiAuthenticationExceptionModel, HttpStatus.UNAUTHORIZED);

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> getServerExceptionHandler(@NotNull Exception exception) {

        if (exception instanceof ExpiredJwtException) {

            ApiAuthenticationExceptionModel apiAuthenticationExceptionModel = new ApiAuthenticationExceptionModel(
                    HttpStatus.UNAUTHORIZED.value(),
                    HttpStatus.UNAUTHORIZED,
                    "Authentication failed",
                    exception.getMessage(),
                    httpServletRequest.getRequestURL().toString(),
                    ZonedDateTime.now(ZoneId.of("Z"))
            );

            return new ResponseEntity<>(apiAuthenticationExceptionModel, HttpStatus.UNAUTHORIZED);

        }

        logger.error("Something went wrong which is causing the application to fail: ", exception);

        ApiExceptionModel apiExceptionModel = new ApiExceptionModel(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                exception.getCause(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private JwtExceptionType getExceptionType(Exception exception) {

        if (exception instanceof ExpiredJwtException) {

            return JwtExceptionType.ExpiredJwtException;

        }

        return JwtExceptionType.Exception;

    }

}
