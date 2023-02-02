package com.marathon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PerformanceNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(PerformanceNotFoundException exception) {
        return new ResponseEntity<>("Such performance doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RaceNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(RaceNotFoundException exception) {
        return new ResponseEntity<>("Race with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(TeamNotFoundException exception) {
        return new ResponseEntity<>("Team doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
