package com.winchesters.devopsify.exception.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winchesters.devopsify.exception.GeneralException;
import org.kohsuke.github.HttpException;

import java.util.Map;

public class GithubException extends GeneralException {
    public GithubException(String message) {
        super("HttpException", message);
    }
}
