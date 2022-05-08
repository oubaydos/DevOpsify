package com.winchesters.devopsify.exception.user;

import com.winchesters.devopsify.exception.GeneralException;

public class InvalidUsernameException extends GeneralException {
    public InvalidUsernameException(String msg) {
        super("InvalidUsernameException",msg);
    }
}
