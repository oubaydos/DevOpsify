package com.winchesters.devopsify.exception;

public class UserCredentialsNotFoundException extends GeneralException {
    public UserCredentialsNotFoundException() {
        super(
                "UserCredentialsNotFoundException",
                "the credentials information aka username and personal access token for this user is not found in the database"
        );
    }

    public UserCredentialsNotFoundException(String username) {
        super(
                "UserCredentialsNotFoundException",
                "the credentials information aka username and personal access token for " + username + " is not found in the database"
        );
    }
}
