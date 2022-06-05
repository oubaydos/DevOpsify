package com.winchesters.devopsify.controller;

import com.winchesters.devopsify.dto.error.ErrorResponseDto;
import com.winchesters.devopsify.exception.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public interface GeneralControllerAdvice {
    Logger LOG = LoggerFactory.getLogger(GeneralControllerAdvice.class);

    default ResponseEntity<ErrorResponseDto> handleException(
            int statusCode, Exception exception) {

        LOG.debug("error response {}", exception.getMessage());
        if (exception instanceof GeneralException) {
            return ResponseEntity
                    .status(statusCode)
                    .body(
                            new ErrorResponseDto(
                                    HttpStatus.resolve(statusCode),
                                    Instant.now(),
                                    true,
                                    exception
                            )
                    );
        }
        return ResponseEntity
                .status(statusCode)
                .body(
                        new ErrorResponseDto(
                                HttpStatus.resolve(statusCode),
                                Instant.now(),
                                exception
                        )
                );

    }
}
