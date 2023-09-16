package com.devdahcoder.exception.api.handler;

import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.exception.api.ApiNotFoundException;
import com.devdahcoder.exception.model.ApiBadRequestExceptionModel;
import com.devdahcoder.exception.model.ApiBadRequestFieldModel;
import com.devdahcoder.exception.model.ApiExceptionModel;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    private HttpServletRequest httpServletRequest;

    public ApiExceptionHandler(HttpServletRequest httpServletRequest) {

        this.httpServletRequest = httpServletRequest;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiBadRequestExceptionModel<ApiBadRequestFieldModel>> getRequestHandler(@NotNull MethodArgumentNotValidException methodArgumentNotValidException) {

        ApiBadRequestExceptionModel<ApiBadRequestFieldModel> apiBadRequestExceptionModel = new ApiBadRequestExceptionModel<>(
                HttpStatus.BAD_REQUEST,
                "Invalid input",
                HttpStatus.BAD_REQUEST.value(),
                methodArgumentNotValidException
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(requestError -> new ApiBadRequestFieldModel(requestError.getField(), requestError.getDefaultMessage()))
                        .toList()
        );

        return new ResponseEntity<>(apiBadRequestExceptionModel, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ApiAlreadyExistException.class)
    public ResponseEntity<ApiExceptionModel> getAlreadyExistHandler(ApiAlreadyExistException apiAlreadyExistException) {

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

}
