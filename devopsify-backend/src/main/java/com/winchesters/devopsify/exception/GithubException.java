package com.winchesters.devopsify.exception;

public class GithubException extends GeneralException{
    public GithubException(Exception exception) {
        super(exception);
    }
}
