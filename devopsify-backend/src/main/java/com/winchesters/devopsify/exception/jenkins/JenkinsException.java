package com.winchesters.devopsify.exception.jenkins;

import com.winchesters.devopsify.exception.GeneralException;

public class JenkinsException extends GeneralException {
    public JenkinsException(String code, String message) {
        super(code, message);
    }
}
