package com.devdahcoder.user.repository;

import com.devdahcoder.exception.api.ApiNotFoundException;
import com.devdahcoder.exception.database.DatabaseBadGrammarException;
import com.devdahcoder.exception.database.DatabaseDataAccessException;
import com.devdahcoder.exception.database.DatabaseDataAccessResourceFailureException;
import com.devdahcoder.exception.database.DatabaseDataIntegrityException;
import com.devdahcoder.user.contract.UserServiceInterface;
import com.devdahcoder.user.model.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<UserMapperModel> findAllUsers(String order, int limit, int offset) {

        String sqlQuery = "SELECT * FROM todo.user ORDER BY ? LIMIT ? OFFSET ?";

        try {

            return jdbcClient
                    .sql(sqlQuery)
                    .param(order)
                    .param(limit)
                    .param(offset)
                    .query(UserMapperModel.class)
                    .list();

        } catch (BadSqlGrammarException exception) {

            logger.error("Your sql query contains syntax errors: {}", exception.getSql());

            throw new DatabaseBadGrammarException("findAllUser: ", sqlQuery, exception.getSQLException());

        } catch (DataAccessException exception) {

            logger.error("Something went wrong with sql query: {0}", exception);

            throw new DatabaseDataAccessException("Something went wrong while retrieving data from the database", exception);

        }

    }

    /**
     * Creates a user in the database.
     *
     * @param user The user to create.
     * @return A status message indicating success or failure.
     * @throws DatabaseBadGrammarException If a SQL grammar error occurs.
     * @throws DatabaseDataIntegrityException If a data integrity violation occurs.
     * @throws DatabaseDataAccessException If a data access error occurs.
     */
    @Override
    public String createUser(@NotNull CreateUserModel user) {

        String sqlQuery = "INSERT INTO todo.user(userId, firstName, lastName, email, username, password, gender, role) values(?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            var created = jdbcClient
                    .sql(sqlQuery)
                    .params(List.of(user.getUserId().toString(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(), "MALE", user.getRole().toString()))
                    .update();

            return created == 1 ? "Created user: " + user.getUsername() : "Could not create user: " + user.getUsername();

        } catch (BadSqlGrammarException exception) {

            logger.error("Your sql query contains syntax errors: {}", exception.getSql());

            throw new DatabaseBadGrammarException("DB-001", "SQL query syntax error: " + exception.getSql(), exception.getSQLException());

        } catch (DataIntegrityViolationException exception) {

            logger.error("Data integrity violation while creating user: {}", exception.getMessage());

            throw new DatabaseDataIntegrityException("Could not create user", exception);

        } catch (DataAccessResourceFailureException exception) {

            logger.error("No connection could be made to a database: {}", exception.getMessage());

            throw new DatabaseDataAccessResourceFailureException("Connection could not be made to a database", exception);

        } catch (DataAccessException exception) {

            logger.error("Something went wrong with sql query: {}", exception.getMessage());

            throw new DatabaseDataAccessException("Something went wrong while retrieving data from the database", exception);

        }

    }

    @Override
    public String authenticateUser(AuthenticateUserModel authenticateUserModel) {

        return null;

    }

    @Override
    public Optional<UserMapperModel> getUserById(@NotNull UUID userId) {

        try {

            var userFound = jdbcClient.sql("SELECT * FROM todo.user WHERE userId = :userId")
                    .param("userId", userId.toString())
                    .query(UserMapperModel.class)
                    .optional();

            return Optional.ofNullable(userFound.orElseThrow(() -> new ApiNotFoundException("User with userId " + userId + " could not be found")));

        } catch (DataAccessException exception) {

            logger.error("Something went wrong with sql query: {}", exception.getMessage());

            throw new DatabaseDataAccessException("Something went wrong while retrieving data from the database", exception);

        }

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

    @Override
    public int countUser() {

        Optional<Integer> countOptional = jdbcClient.sql("SELECT count(*) FROM todo.user").query(Integer.class).optional();

        return countOptional.orElse(0);

    }

}
