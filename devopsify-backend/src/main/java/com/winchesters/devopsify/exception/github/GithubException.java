package com.winchesters.devopsify.exception.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winchesters.devopsify.exception.GeneralException;
import org.kohsuke.github.HttpException;

import java.util.Map;

public class GithubException extends GeneralException {
//    public GithubException(HttpException exception) {
//        super(exception.getClass().getSimpleName(),exception.getLocalizedMessage());
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Map<String,Object> map = mapper.readValue(exception.getLocalizedMessage(), Map.class);
//            System.err.println(map.values());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }

    public GithubException(String message) {
        super("HttpException", message);
    }
}
