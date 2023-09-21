package com.devdahcoder.exception.api;

import org.springframework.security.authentication.BadCredentialsException;

public class ApiBadCredentialException extends BadCredentialsException {

    public ApiBadCredentialException(String msg) {

        super(msg);

    }

    public ApiBadCredentialException(String msg, Throwable cause) {

        super(msg, cause);

    }

}
