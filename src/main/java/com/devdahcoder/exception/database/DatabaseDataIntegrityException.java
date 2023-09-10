package com.devdahcoder.exception.database;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseDataIntegrityException extends DataIntegrityViolationException {

    public DatabaseDataIntegrityException(String msg) {

        super(msg);

    }

    public DatabaseDataIntegrityException(String msg, Throwable cause) {

        super(msg, cause);

    }

}
