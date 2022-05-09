package com.winchesters.devopsify.controller.user;

import com.winchesters.devopsify.controller.GeneralControllerAdviceImpl;
import com.winchesters.devopsify.dto.ErrorResponseDto;
import com.winchesters.devopsify.exception.user.InvalidEmailException;
import com.winchesters.devopsify.exception.user.InvalidUsernameException;
import com.winchesters.devopsify.exception.user.UserNotAuthenticatedException;
import com.winchesters.devopsify.exception.user.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = UserController.class)
public class UserControllerAdvice extends GeneralControllerAdviceImpl {


    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidEmailException(
            InvalidEmailException exception
    ) {
        return handleException(HttpStatus.BAD_REQUEST.value(), exception);
    }
    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidUsernameException(
            InvalidUsernameException exception
    ) {
        return handleException(HttpStatus.BAD_REQUEST.value(), exception);
    }
    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotAuthenticatedException(
            UserNotAuthenticatedException exception
    ) {
        return handleException(HttpStatus.UNAUTHORIZED.value(), exception);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(
            UserNotFoundException exception
    ) {
        return handleException(HttpStatus.NOT_FOUND.value(), exception);
    }
}
