package com.devdahcoder.exception.api.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
