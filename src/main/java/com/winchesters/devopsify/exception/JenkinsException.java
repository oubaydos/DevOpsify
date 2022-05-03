package com.winchesters.devopsify.exception;

public class JenkinsException extends GeneralException{
    public JenkinsException(String code, String message) {
        super(code, message);
    }
}
