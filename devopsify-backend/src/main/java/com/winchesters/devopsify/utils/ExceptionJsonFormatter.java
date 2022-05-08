package com.winchesters.devopsify.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winchesters.devopsify.exception.GeneralException;
import com.winchesters.devopsify.exception.GithubException;
import org.kohsuke.github.HttpException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record ExceptionJsonFormatter(String code, String message) {
    public static ExceptionJsonFormatter convertExceptionToJson(Exception exception) {
        if (exception instanceof GeneralException) {
            return new ExceptionJsonFormatter(((GeneralException) exception).getCode(), exception.getMessage());
        }
        return new ExceptionJsonFormatter(exception.getClass().getSimpleName(), exception.getMessage());
    }

    public static GithubException HttpExceptionToGithubException(HttpException exception) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(exception.getLocalizedMessage(), Map.class);
        System.err.println(map.values());
        StringBuilder message = new StringBuilder();
        if (exception.getResponseMessage() != null){
            message.append(exception.getResponseMessage());
            message.append(" : ");
        }
        message.append(Optional.ofNullable(map.get("message")).orElse(""));
        message.append(" : ");
        List<Map<String, String>> errors = (List<Map<String, String>>) Optional.ofNullable(map.get("errors")).orElse(null);
        if (errors != null && errors.size()>0){
            for (Map<String, String> error : errors ){
                if (error == null) continue;
                message.append(Optional.ofNullable(error.get("message")).orElse(""));
                message.append("\n");
            }
        }
        return new GithubException(message.toString());
    }
}