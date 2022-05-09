package com.winchesters.devopsify.exception.jwt;

import com.winchesters.devopsify.exception.GeneralException;

public class JwtTokenNotValidException extends GeneralException {

    public JwtTokenNotValidException() {
        super("JwtTokenNotValidException", "Invalid jwt token");
    }
}
