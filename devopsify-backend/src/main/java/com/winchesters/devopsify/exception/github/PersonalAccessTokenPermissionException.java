package com.winchesters.devopsify.exception.github;


import com.winchesters.devopsify.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class PersonalAccessTokenPermissionException extends GeneralException {
    public PersonalAccessTokenPermissionException() {
        super("PersonalAccessTokenPermissionException", "the given access token does not contain all required permissions\n please add all available permissions for the token (https://github.com/settings/tokens)");
    }
}
