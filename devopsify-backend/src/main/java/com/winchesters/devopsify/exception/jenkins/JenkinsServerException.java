package com.winchesters.devopsify.exception.jenkins;

public class JenkinsServerException extends JenkinsException {
    public JenkinsServerException(String code, String message) {
        super(code,message);
    }
}
