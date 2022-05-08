package com.winchesters.devopsify.utils;

import com.winchesters.devopsify.exception.GeneralException;

public record ExceptionJsonFormatter(String code, String message) {
    public static ExceptionJsonFormatter convertExceptionToJson(Exception exception) {
        if (exception instanceof GeneralException) {
            return new ExceptionJsonFormatter(((GeneralException) exception).getCode(), exception.getMessage());
        }
        return new ExceptionJsonFormatter(exception.getClass().getSimpleName(), exception.getMessage());
    }
}