package com.winchesters.devopsify.exception;

public class JwtTokenNotValidException extends GeneralException{

    public JwtTokenNotValidException() {
        super("JwtTokenNotValidException", "Invalid jwt token");
    }
}
