package com.winchesters.devopsify.controller.git;

import com.winchesters.devopsify.controller.project.ProjectControllerAdvice;
import com.winchesters.devopsify.dto.ErrorResponseDto;
import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.exception.git.GitNotInstalledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GitControllerAdvice extends ProjectControllerAdvice {

    @ExceptionHandler(GitException.class)
    public ResponseEntity<ErrorResponseDto> handleGitException(
            int statusCode,
            GitException exception
    ) {
        return handleException(statusCode,exception);
    }


    @ExceptionHandler(GitNotInstalledException.class)
    public ResponseEntity<ErrorResponseDto> handleGitNotInstalledException(
            GitNotInstalledException exception
    ) {
        return handleGitException(HttpStatus.UNAUTHORIZED.value(),exception);
    }

}
