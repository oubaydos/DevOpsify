package com.winchesters.devopsify.controller.git;

import com.winchesters.devopsify.controller.project.ProjectControllerAdvice;
import com.winchesters.devopsify.dto.error.ErrorResponseDto;
import com.winchesters.devopsify.exception.git.GitAPIException;
import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.exception.git.GitInternalException;
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

    @ExceptionHandler(GitException.class)
    public ResponseEntity<ErrorResponseDto> handleGitException(
            GitException exception
    ) {
        return handleGitException(HttpStatus.BAD_REQUEST.value(),exception);
    }


    @ExceptionHandler(GitNotInstalledException.class)
    public ResponseEntity<ErrorResponseDto> handleGitNotInstalledException(
            GitNotInstalledException exception
    ) {
        return handleGitException(HttpStatus.UNAUTHORIZED.value(),exception);
    }

    @ExceptionHandler(GitAPIException.class)
    public ResponseEntity<ErrorResponseDto> handleGitAPIException(
            GitAPIException exception
    ) {
        return handleGitException(HttpStatus.BAD_GATEWAY.value(), exception);
    }

    @ExceptionHandler(GitInternalException.class)
    public ResponseEntity<ErrorResponseDto> handleGitInternalException(
            GitInternalException exception
    ) {
        return handleGitException(HttpStatus.BAD_REQUEST.value(), exception);
    }
}
