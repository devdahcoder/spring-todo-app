package com.devdahcoder.exception.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;

public class ApiJwtExpiredException extends ExpiredJwtException {

    public ApiJwtExpiredException(Header header, Claims claims, String message) {

        super(header, claims, message);

    }

    public ApiJwtExpiredException(Header header, Claims claims, String message, Throwable cause) {

        super(header, claims, message, cause);

    }

}
