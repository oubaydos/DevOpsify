package com.winchesters.devopsify.exception;

public class InvalidUsernameException extends GeneralException{
    public InvalidUsernameException(String msg) {
        super("InvalidUsernameException",msg);
    }
}
