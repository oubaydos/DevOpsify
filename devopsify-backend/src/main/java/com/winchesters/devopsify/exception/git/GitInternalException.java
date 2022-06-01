package com.winchesters.devopsify.exception.git;

public class GitInternalException extends GitException{
    public GitInternalException(Exception exception) {
        super(exception);
    }
}
