package com.winchesters.devopsify.controller.project;

import com.winchesters.devopsify.controller.GeneralControllerAdviceImpl;
import com.winchesters.devopsify.dto.ErrorResponseDto;
import com.winchesters.devopsify.exception.project.ProjectNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = ProjectController.class)
public class ProjectControllerAdvice extends GeneralControllerAdviceImpl {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleException(
            ProjectNotFoundException exception
    ) {
        return handleException(HttpStatus.NOT_FOUND.value(), exception);
    }
}
