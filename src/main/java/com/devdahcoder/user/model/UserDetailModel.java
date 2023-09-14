package com.devdahcoder.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailModel implements UserDetails {

    private final UserModel userModel;

    public UserDetailModel(UserModel userModel) {

        this.userModel = userModel;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(userModel.getRole().toString()));

    }

    @Override
    public String getPassword() {

        return userModel.getPassword();

    }

    @Override
    public String getUsername() {

        return userModel.getUsername();

    }

    @Override
    public boolean isAccountNonExpired() {

        return true;

    }

    @Override
    public boolean isAccountNonLocked() {

        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;

    }

    @Override
    public boolean isEnabled() {

        return true;

    }

}
