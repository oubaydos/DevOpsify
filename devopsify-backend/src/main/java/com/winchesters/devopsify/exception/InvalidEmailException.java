package com.winchesters.devopsify.exception;

public class InvalidEmailException extends GeneralException{


    public InvalidEmailException(String message) {
        super("InvalidUsernameException", message);
    }
}
