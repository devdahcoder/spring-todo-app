package com.devdahcoder.exception.database;

import org.springframework.dao.DataAccessResourceFailureException;

public class DatabaseDataAccessResourceFailureException extends DataAccessResourceFailureException {

    public DatabaseDataAccessResourceFailureException(String msg) {

        super(msg);

    }

    public DatabaseDataAccessResourceFailureException(String msg, Throwable cause) {

        super(msg, cause);

    }

}
