package com.devdahcoder.exception.handler;

import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.exception.api.ApiBadCredentialException;
import com.devdahcoder.exception.api.ApiNotFoundException;
import com.devdahcoder.exception.model.ApiAuthenticationExceptionModel;
import com.devdahcoder.response.error.BadRequestApiResponseError;
import com.devdahcoder.response.error.BadRequestFieldApiResponseError;
import com.devdahcoder.exception.model.ApiExceptionModel;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    private HttpServletRequest httpServletRequest;

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

}
