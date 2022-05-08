package com.winchesters.devopsify.controller.github;

import com.winchesters.devopsify.controller.GeneralControllerAdvice;
import com.winchesters.devopsify.dto.ErrorResponseDto;
import com.winchesters.devopsify.exception.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public interface GithubControllerAdvice extends GeneralControllerAdvice {
    Logger LOG = LoggerFactory.getLogger(GithubControllerAdvice.class);
    default <E extends GeneralException> ResponseEntity<ErrorResponseDto> handleGithubException(
            int statusCode, E exception) {
        LOG.debug("error response {}", exception.getMessage());
        return handleException(statusCode,exception);
    }
}
