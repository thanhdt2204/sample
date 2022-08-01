package com.example.sample.exception;

import com.example.sample.config.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException() {
        return new ResponseEntity<>(Constants.Error.USERNAME_OR_PASSWORD_INCORRECT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        AtomicReference<String> message = new AtomicReference<>("");
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        constraintViolations.forEach(item -> {
            message.set(message + "[" + item.getPropertyPath() + ": " + item.getMessage() + "]");
        });
        return new ResponseEntity<>(message.get(), HttpStatus.BAD_REQUEST);
    }

}
