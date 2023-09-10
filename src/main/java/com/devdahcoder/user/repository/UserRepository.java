package com.devdahcoder.user.repository;

import com.devdahcoder.exception.database.DatabaseBadGrammarException;
import com.devdahcoder.exception.database.DatabaseDataAccessException;
import com.devdahcoder.user.contract.UserServiceInterface;
import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserDetailModel;
import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.user.model.UserModel;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class UserRepository implements UserServiceInterface, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) { this.jdbcClient = jdbcClient; }

    /**
     * Load a user by their username.
     *
     * @param username The username of the user to load.
     * @return A UserDetailModel which inherits UserDetails.
     * @throws UsernameNotFoundException If the username is not found.
     */
    @Override
    public UserDetailModel loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            Optional<UserModel> user = jdbcClient
                    .sql("SELECT * FROM todo.user WHERE username = :username")
                    .param("username", username)
                    .query(UserModel.class)
                    .optional();

            UserModel foundUser = user.orElseThrow(() -> new UsernameNotFoundException("Username and password not found"));

            return new UserDetailModel(foundUser);

        } catch (DataAccessException exception) {

            logger.error("Something went wrong with sql query: {0}", exception);

            throw new DatabaseDataAccessException("Something went wrong while retrieving data from the database", exception);

        }

    }

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

    @Override
    public String createUser(@NotNull CreateUserModel user) {

        var updated = jdbcClient
                .sql("INSERT INTO todo.user(userId, firstName, lastName, email, username, password, role) " +
                        "values(?, ?, ?, ?, ?, ?, ?)")
                .param(List.of(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole()))
                .update();

        return updated == 1 ? "Updated " + user.getUsername() : "Could not update";

    }

    @Override
    public boolean userExists(String username) {

        return jdbcClient
                .sql("SELECT * FROM todo.user WHERE username = :username")
                .param("username", username)
                .query(UserModel.class)
                .optional()
                .isPresent();

    }

}
