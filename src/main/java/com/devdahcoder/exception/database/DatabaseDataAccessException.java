package com.devdahcoder.exception.database;

import org.springframework.dao.DataAccessException;

public class DatabaseDataAccessException extends DataAccessException {

    public DatabaseDataAccessException(String msg) {

        super(msg);
    }

    public DatabaseDataAccessException(String msg, Throwable cause) {

        super(msg, cause);
    }

}
