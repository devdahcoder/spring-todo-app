package com.devdahcoder.exception.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExceptionModel {

    private int statusCode;
    private String message;
    private Throwable throwable;
    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;

    public ApiExceptionModel(int statusCode, String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {

        this.statusCode = statusCode;
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;

    }

    public int getStatusCode() {

        return statusCode;

    }

    public String getMessage() {

        return message;

    }

    public Throwable getThrowable() {

        return throwable;

    }

    public HttpStatus getHttpStatus() {

        return httpStatus;

    }

    public ZonedDateTime getZonedDateTime() {

        return zonedDateTime;

    }

}
