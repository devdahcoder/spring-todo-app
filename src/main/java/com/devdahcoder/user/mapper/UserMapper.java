package com.devdahcoder.user.mapper;

import com.devdahcoder.user.contract.GenderEnum;
import com.devdahcoder.user.contract.RoleEnum;
import com.devdahcoder.user.model.UserMapperModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<UserMapperModel> {

    @Override
    public UserMapperModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        UserMapperModel userMapperModel = new UserMapperModel();

        userMapperModel.setId(resultSet.getInt("id"));
        userMapperModel.setUserId(UUID.fromString(resultSet.getString("userId")));
        userMapperModel.setFirstName(resultSet.getString("firstName"));
        userMapperModel.setLastName(resultSet.getString("lastName"));
        userMapperModel.setEmail(resultSet.getString("email"));
        userMapperModel.setGender(GenderEnum.valueOf(resultSet.getString("gender")));
        userMapperModel.setRole(RoleEnum.valueOf(resultSet.getString("role")));

        return userMapperModel;

    }

}
