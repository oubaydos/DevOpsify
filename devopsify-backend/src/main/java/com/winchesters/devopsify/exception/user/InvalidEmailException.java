package com.winchesters.devopsify.exception.user;

import com.winchesters.devopsify.exception.GeneralException;

public class InvalidEmailException extends GeneralException {


    public InvalidEmailException(String message) {
        super("InvalidUsernameException", message);
    }
}
