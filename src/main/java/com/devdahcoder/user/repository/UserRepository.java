package com.devdahcoder.user.repository;

import com.devdahcoder.user.contract.UserServiceInterface;
import com.devdahcoder.user.model.UserMapperModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserRepository implements UserServiceInterface {

    @Autowired
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) { this.jdbcClient = jdbcClient; }

    @Override
    public Iterable<UserMapperModel> findAllUsers() {

        return jdbcClient
                .sql("SELECT * FROM todo.user")
                .query(UserMapperModel.class)
                .list();

    }

}
