package org.damon.st.producer.exceptionhandler;

import org.damon.st.producer.controller.UsersController;
import org.damon.st.producer.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(assignableTypes = UsersController.class)
public class UsersExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return new ResponseEntity<>(
                new ErrorDto(
                        e.getMessage(),
                        System.currentTimeMillis()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
