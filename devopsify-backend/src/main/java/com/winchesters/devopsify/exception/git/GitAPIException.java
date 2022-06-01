package com.winchesters.devopsify.exception.git;

public class GitAPIException extends GitException{
    public GitAPIException(Exception exception) {
        super(exception);
    }
}
