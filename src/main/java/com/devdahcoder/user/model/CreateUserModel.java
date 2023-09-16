package com.devdahcoder.user.model;

import com.devdahcoder.user.contract.GenderEnum;
import com.devdahcoder.user.contract.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class CreateUserModel {

    private UUID userId;
    @NotEmpty( message = "Firstname cannot be empty" )
    private String firstName;
    @NotEmpty( message = "Lastname cannot be empty" )
    private String lastName;
    @Email( message = "Email must be a valid email" )
    @NotEmpty( message = "Email cannot be empty" )
    private String email;
    @NotEmpty( message = "Username cannot be empty" )
    private String username;
    @NotEmpty( message = "Password cannot be empty" )
    private String password;
//    @NotEmpty( message = "Gender cannot be empty" )
    private GenderEnum gender;
    private RoleEnum role;

    public CreateUserModel(String firstName, String lastName, String email, String username, String password, GenderEnum gender, RoleEnum role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
        this.gender = gender;

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

    public GenderEnum getGender() {

        return gender;

    }

    public void setGender(GenderEnum gender) {

        this.gender = gender;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof CreateUserModel that)) return false;

        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && getRole() == that.getRole() && getGender() == that.getGender();

    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserId(), getFirstName(), getLastName(), getEmail(), getUsername(), getPassword(), getRole(), getGender());

    }

    @Override
    public String toString() {

        return "CreateUserModel{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", gender=" + gender +
                '}';

    }

}
