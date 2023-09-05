package com.devdahcoder.user.model;

import com.devdahcoder.user.contract.GenderEnum;
import com.devdahcoder.user.contract.RoleEnum;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class UserModel {

    private int id;
    private UUID userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String country;
    private Date dateOfBirth;
    private String phoneNumber;
    private String website;
    private String password;
    private GenderEnum gender;
    private RoleEnum role;

    public UserModel() {

    }

    public UserModel(int id, UUID userId, String firstName, String lastName, String username, String email, String country, Date dateOfBirth, String phoneNumber, String website, String password, GenderEnum gender, RoleEnum role) {

        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.password = password;
        this.gender = gender;
        this.role = role;

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

    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

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

    public String getCountry() {

        return country;

    }

    public void setCountry(String country) {

        this.country = country;

    }

    public Date getDateOfBirth() {

        return dateOfBirth;

    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;

    }

    public String getPhoneNumber() {

        return phoneNumber;

    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;

    }

    public String getWebsite() {

        return website;

    }

    public void setWebsite(String website) {

        this.website = website;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof UserModel userModel)) return false;

        return getId() == userModel.getId() && Objects.equals(getUserId(), userModel.getUserId()) && Objects.equals(getFirstName(), userModel.getFirstName()) && Objects.equals(getLastName(), userModel.getLastName()) && Objects.equals(getUsername(), userModel.getUsername()) && Objects.equals(getEmail(), userModel.getEmail()) && Objects.equals(getCountry(), userModel.getCountry()) && Objects.equals(getDateOfBirth(), userModel.getDateOfBirth()) && Objects.equals(getPhoneNumber(), userModel.getPhoneNumber()) && Objects.equals(getWebsite(), userModel.getWebsite()) && Objects.equals(getPassword(), userModel.getPassword()) && getGender() == userModel.getGender() && getRole() == userModel.getRole();

    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUserId(), getFirstName(), getLastName(), getUsername(), getEmail(), getCountry(), getDateOfBirth(), getPhoneNumber(), getWebsite(), getPassword(), getGender(), getRole());

    }

}
