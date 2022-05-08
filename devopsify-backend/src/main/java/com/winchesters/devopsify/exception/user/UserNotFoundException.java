package com.winchesters.devopsify.exception.user;

import com.winchesters.devopsify.exception.GeneralException;

public class UserNotFoundException extends GeneralException {
    public UserNotFoundException(String username) {
        super("UserNotFoundException", String.format("user wih username %s not found", username));
    }

    public UserNotFoundException(Long userId) {
        super("UserNotFoundException", String.format("user wih id %s not found", userId));
    }

    public UserNotFoundException() {
        super("UserNotFoundException", "user not found");
    }
}
