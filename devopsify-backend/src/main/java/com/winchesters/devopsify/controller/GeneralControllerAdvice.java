package com.winchesters.devopsify.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.winchesters.devopsify.dto.ErrorResponseDto;
import com.winchesters.devopsify.exception.GeneralException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.time.Instant;

@Order()
@RestControllerAdvice
public class GeneralControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(GeneralControllerAdvice.class);

    public static ResponseEntity<ErrorResponseDto> handleException(
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

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        LOG.error("Technical exception", exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class,
            HttpMessageConversionException.class,
            ValidationException.class,
            MissingRequestHeaderException.class,
            UnrecognizedPropertyException.class})
    public void handleBadRequest(Exception exception) {
        LOG.debug("Bad request {}", exception.toString());
    }



}
