package com.winchesters.devopsify.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.winchesters.devopsify.dto.error.ErrorResponseDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Order()
@RestControllerAdvice
public class GeneralControllerAdviceImpl implements GeneralControllerAdvice {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        LOG.error("Technical exception", exception);
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
            {
                    ConstraintViolationException.class,
                    HttpMessageConversionException.class,
                    ValidationException.class,
                    MissingRequestHeaderException.class,
                    UnrecognizedPropertyException.class,
                    BindException.class
            }
    )
    public ResponseEntity<ErrorResponseDto> handleBadRequest(Exception exception) {
        LOG.debug("Bad request {}", exception.toString());
        return handleException(HttpStatus.BAD_REQUEST.value(), exception);

    }


}
