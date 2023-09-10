package com.devdahcoder.exception.api.handler;

import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.exception.model.ApiExceptionModel;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private HttpServletRequest httpServletRequest;

    public ApiExceptionHandler(HttpServletRequest httpServletRequest) {

        this.httpServletRequest = httpServletRequest;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> getRequestHandler(@NotNull MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> requestErrors = new HashMap<>();

        methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .forEach(requestError -> requestErrors.put(requestError.getField(), requestError.getDefaultMessage()));

        return requestErrors;

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

}
