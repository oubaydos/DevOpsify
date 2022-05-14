package com.winchesters.devopsify.controller.jenkins;

import com.winchesters.devopsify.controller.project.ProjectControllerAdvice;
import com.winchesters.devopsify.dto.ErrorResponseDto;
import com.winchesters.devopsify.exception.jenkins.JenkinsException;
import com.winchesters.devopsify.exception.jenkins.JenkinsServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class JenkinsControllerAdvice extends ProjectControllerAdvice {

    @ExceptionHandler(JenkinsException.class)
    public ResponseEntity<ErrorResponseDto> handleJenkinsException(
            int statusCode,
            JenkinsException exception
    ) {
        return handleException(statusCode, exception);
    }

    @ExceptionHandler(JenkinsServerException.class)
    public ResponseEntity<ErrorResponseDto> handleJenkinsServerException(
            JenkinsServerException exception
    ) {
        return handleJenkinsException(HttpStatus.NOT_ACCEPTABLE.value(), exception);
    }

}
