package com.spring.boot.jpa.Persistence.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.hibernate.query.sqm.PathElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PathElementException.class)
    public ResponseEntity<String> handlePathElementException(PathElementException exception){
        String columnName = exception.getMessage().split("'")[1];
        return new ResponseEntity<>("named column does not exist: " + columnName, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception){
        return new ResponseEntity<>(exception.getMessage() + " in System records", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        return new ResponseEntity<>(exception.getMostSpecificCause().getMessage(), HttpStatus.ALREADY_REPORTED);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(exception.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
