package com.winchesters.devopsify.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Order()
@RestControllerAdvice
public class GeneralControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(GeneralControllerAdvice.class);

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
