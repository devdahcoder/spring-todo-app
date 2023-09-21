package com.devdahcoder.exception.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Objects;

public class ApiAuthenticationExceptionModel {

    private final int statusCode;
    private final HttpStatus httpStatus;
    private final String error;
    private final String message;
    private final String path;
    private final ZonedDateTime zonedDateTime;

    public ApiAuthenticationExceptionModel(int statusCode, HttpStatus httpStatus, String error, String message, String path, ZonedDateTime zonedDateTime) {

        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.error = error;
        this.message = message;
        this.path = path;
        this.zonedDateTime = zonedDateTime;

    }

    public int getStatusCode() {

        return statusCode;

    }

    public HttpStatus getHttpStatus() {

        return httpStatus;

    }

    public String getError() {

        return error;

    }

    public String getMessage() {

        return message;

    }

    public String getPath() {

        return path;

    }

    public ZonedDateTime getZonedDateTime() {

        return zonedDateTime;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof ApiAuthenticationExceptionModel that)) return false;

        return getStatusCode() == that.getStatusCode() && getHttpStatus() == that.getHttpStatus() && Objects.equals(getError(), that.getError()) && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getPath(), that.getPath()) && Objects.equals(getZonedDateTime(), that.getZonedDateTime());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getStatusCode(), getHttpStatus(), getError(), getMessage(), getPath(), getZonedDateTime());

    }

    @Override
    public String toString() {

        return "ApiAuthenticationExceptionModel{" +
                "statusCode=" + statusCode +
                ", httpStatus=" + httpStatus +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", zonedDateTime=" + zonedDateTime +
                '}';

    }

}
