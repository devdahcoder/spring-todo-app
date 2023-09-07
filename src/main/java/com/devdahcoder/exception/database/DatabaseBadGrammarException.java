package com.devdahcoder.exception.database;

import org.springframework.jdbc.BadSqlGrammarException;

import java.sql.SQLException;

public class DatabaseBadGrammarException extends BadSqlGrammarException {

    /**
     * Constructor for BadSqlGrammarException.
     *
     * @param task name of a current task
     * @param sql  the offending SQL statement
     * @param ex   the root cause
     */
    public DatabaseBadGrammarException(String task, String sql, SQLException ex) {

        super(task, sql, ex);
    }

}
