package com.marathon.exception.global;

import com.marathon.exception.*;
import io.micrometer.core.instrument.config.validate.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PerformanceNotFoundException.class)
    public ResponseEntity<Object> handlePerformanceNotFoundException(PerformanceNotFoundException exception) {
        return new ResponseEntity<>("Such performance doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RaceNotFoundException.class)
    public ResponseEntity<Object> handleRaceNotFoundException(RaceNotFoundException exception) {
        return new ResponseEntity<>("Race with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<Object> handleTeamNotFoundException(TeamNotFoundException exception) {
        return new ResponseEntity<>("Team doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PerformanceByUserExistsException.class)
    public ResponseEntity<Object> handlePerformanceByUserExistsException(PerformanceByUserExistsException exception) {
        return new ResponseEntity<>("Performance by given user already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserWithGivenEmailExistsException.class)
    public ResponseEntity<Object> handleUserWithGivenEmailExistsException(UserWithGivenEmailExistsException exception) {
        return new ResponseEntity<>("User with given email already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldMustNotBeNullException.class)
    public ResponseEntity<Object> handleFieldMustNotBeNullException(FieldMustNotBeNullException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidEmailAddressException.class)
    public ResponseEntity<Object> handleNotValidEmailAddressException(NotValidEmailAddressException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidNameException.class)
    public ResponseEntity<Object> handleNotValidNameException(NotValidNameException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidCodeException.class)
    public ResponseEntity<Object> handleNotValidCodeException(NotValidCodeException exception) {
        return new ResponseEntity<>("Not a valid postal code. It should be format 01-234", HttpStatus.BAD_REQUEST);
    }
}
