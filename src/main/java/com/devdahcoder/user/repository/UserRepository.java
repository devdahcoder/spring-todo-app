package com.devdahcoder.user.repository;

import com.devdahcoder.exception.database.DatabaseBadGrammarException;
import com.devdahcoder.exception.database.DatabaseDataAccessException;
import com.devdahcoder.user.contract.UserServiceInterface;
import com.devdahcoder.user.model.UserMapperModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserRepository implements UserServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) { this.jdbcClient = jdbcClient; }

    @Override
    public Iterable<UserMapperModel> findAllUsers() {

        String sqlQuery = "SELECT * FROM todo.user";

        try {

            return jdbcClient
                    .sql(sqlQuery)
                    .query(UserMapperModel.class)
                    .list();

        } catch (BadSqlGrammarException exception) {

            logger.error("Your sql query contains grammatical errors: {}", exception.getSql());

            throw new DatabaseBadGrammarException("findAllUser: ", sqlQuery, exception.getSQLException());

        } catch (DataAccessException exception) {

            logger.error("Something went wrong with sql query: {0}", exception);

            throw new DatabaseDataAccessException("Something went wrong while retrieving data from the database", exception);

        }

    }

}
