package com.devdahcoder.exception.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Objects;

public class ApiExceptionModel {

    private final int statusCode;
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof ApiExceptionModel that)) return false;

        return getStatusCode() == that.getStatusCode() && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getThrowable(), that.getThrowable()) && getHttpStatus() == that.getHttpStatus() && Objects.equals(getZonedDateTime(), that.getZonedDateTime());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getStatusCode(), getMessage(), getThrowable(), getHttpStatus(), getZonedDateTime());

    }

    @Override
    public String toString() {

        return "ApiExceptionModel{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", throwable=" + throwable +
                ", httpStatus=" + httpStatus +
                ", zonedDateTime=" + zonedDateTime +
                '}';

    }

}
