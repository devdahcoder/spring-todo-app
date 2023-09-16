package com.devdahcoder.exception.model;

public class ApiBadRequestFieldModel {
    private String field;
    private String message;

    public ApiBadRequestFieldModel(String field, String message) {

        this.field = field;
        this.message = message;

    }

    public String getField() {

        return field;

    }

    public String getMessage() {

        return message;

    }

}
