package com.winchesters.devopsify.controller.github;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.winchesters.devopsify.dto.error.ErrorResponseDto;
import com.winchesters.devopsify.exception.github.PersonalAccessTokenPermissionException;
import org.kohsuke.github.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.winchesters.devopsify.utils.ExceptionJsonFormatter.HttpExceptionToGithubException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = GithubController.class)
public class GithubControllerAdviceImpl implements GithubControllerAdvice{
    private static final Logger LOG = LoggerFactory.getLogger(GithubControllerAdvice.class);

    @ExceptionHandler(PersonalAccessTokenPermissionException.class)
    public ResponseEntity<ErrorResponseDto> handleException(
            PersonalAccessTokenPermissionException exception
    ) {
        return handleGithubException(HttpStatus.UNAUTHORIZED.value(), exception);
    }
    // TODO
    // check why isn't this method handling the associated exceptions
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponseDto> handleException(
            HttpException exception
    ) throws JsonProcessingException {
        return handleGithubException(exception.getResponseCode(), HttpExceptionToGithubException(exception));
    }

}
