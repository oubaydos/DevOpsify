package com.winchesters.devopsify.exception.user;

import com.winchesters.devopsify.exception.GeneralException;

public class UserNotAuthenticatedException extends GeneralException {
    public UserNotAuthenticatedException() {
        super("UserNotAuthenticatedException", "user is not authenticated");
    }
}
