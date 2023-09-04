package com.devdahcoder.user.model;

import com.devdahcoder.user.contract.GenderEnum;
import com.devdahcoder.user.contract.RoleEnum;

import java.util.Objects;
import java.util.UUID;

public class UserMapperModel {

    private int id;
    private UUID userId;
    private String email;
    private String lastName;
    private String username;
    private String firstName;
    private GenderEnum gender;
    private RoleEnum role;

    public UserMapperModel() {}

    public UserMapperModel(int id, UUID userId, String firstName, String lastName, String username, String email, GenderEnum gender, RoleEnum role) {

        this.id = id;
        this.email = email;
        this.userId = userId;
        this.gender = gender;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.firstName = firstName;

    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public UUID getUserId() {

        return userId;

    }

    public void setUserId(UUID userId) {

        this.userId = userId;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public String getLastName() {

        return lastName;

    }

    public void setLastName(String lastName) {

        this.lastName = lastName;

    }

    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getFirstName() {

        return firstName;

    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;

    }

    public GenderEnum getGender() {

        return gender;

    }

    public void setGender(GenderEnum gender) {

        this.gender = gender;

    }

    public RoleEnum getRole() {

        return role;

    }

    public void setRole(RoleEnum role) {

        this.role = role;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof UserMapperModel that)) return false;

        return getId() == that.getId() && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getFirstName(), that.getFirstName()) && getGender() == that.getGender() && getRole() == that.getRole();

    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUserId(), getEmail(), getLastName(), getUsername(), getFirstName(), getGender(), getRole());

    }

}
