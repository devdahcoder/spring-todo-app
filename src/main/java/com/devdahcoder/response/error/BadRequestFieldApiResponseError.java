package com.devdahcoder.response.error;

import java.util.Objects;

public class BadRequestFieldApiResponseError {

    private String field;
    private String message;

    public BadRequestFieldApiResponseError(String field, String message) {

        this.field = field;
        this.message = message;

    }

    public String getField() {

        return field;

    }

    public String getMessage() {

        return message;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof BadRequestFieldApiResponseError that)) return false;

        return Objects.equals(getField(), that.getField()) && Objects.equals(getMessage(), that.getMessage());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getField(), getMessage());

    }

    @Override
    public String toString() {

        return "BadRequestFieldApiResponse{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';

    }

}
