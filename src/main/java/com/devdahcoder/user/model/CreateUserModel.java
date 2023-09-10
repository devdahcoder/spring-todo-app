package com.devdahcoder.user.model;

import com.devdahcoder.user.contract.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class CreateUserModel {

    private UUID userId;
    @NotBlank( message = "Firstname cannot be blank" )
    @NotEmpty( message = "Firstname cannot be empty" )
    private String firstName;
    @NotBlank( message = "Lastname cannot be blank" )
    @NotEmpty( message = "Lastname cannot be empty" )
    private String lastName;
    @Email( message = "Email must be a valid email" )
    @NotBlank( message = "Email cannot be blank" )
    @NotEmpty( message = "Email cannot be empty" )
    private String email;
    @NotBlank( message = "Username cannot be blank" )
    @NotEmpty( message = "Username cannot be empty" )
    private String username;
    @NotBlank( message = "Password cannot be black" )
    @NotEmpty( message = "Password cannot be empty" )
    private String password;
    private RoleEnum role;

    public CreateUserModel(String firstName, String lastName, String email, String username, String password, RoleEnum role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;

    }

    public UUID getUserId() {

        return userId;

    }

    public void setUserId(UUID userId) {

        this.userId = userId;

    }

    public String getFirstName() {

        return firstName;

    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;

    }

    public String getLastName() {

        return lastName;

    }

    public void setLastName(String lastName) {

        this.lastName = lastName;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public RoleEnum getRole() {

        return role;
    }

    public void setRole(RoleEnum role) {

        this.role = role;
    }

}
