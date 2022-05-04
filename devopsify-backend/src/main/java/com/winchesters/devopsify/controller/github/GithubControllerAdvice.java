package com.winchesters.devopsify.controller.github;

import com.winchesters.devopsify.exception.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public interface GithubControllerAdvice {
    Logger LOG = LoggerFactory.getLogger(GithubControllerAdvice.class);
    default <E extends GeneralException> ResponseEntity<E> handleGithubException(
            int statusCode, E exception) {
        LOG.debug("error response {}", exception.getMessage());
        return ResponseEntity
                .status(statusCode)
                .body(exception);
    }
}
