package com.winchesters.devopsify.controller.github;


import com.winchesters.devopsify.exception.PersonalAccessTokenPermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = GithubController.class)
public class GithubControllerAdviceImpl implements GithubControllerAdvice{
    private static final Logger LOG = LoggerFactory.getLogger(GithubControllerAdvice.class);

    @ExceptionHandler(PersonalAccessTokenPermissionException.class)
    public ResponseEntity<PersonalAccessTokenPermissionException> handleException(
            PersonalAccessTokenPermissionException exception
    ) {
        return handleGithubException(HttpStatus.UNAUTHORIZED.value(), exception);
    }

}
