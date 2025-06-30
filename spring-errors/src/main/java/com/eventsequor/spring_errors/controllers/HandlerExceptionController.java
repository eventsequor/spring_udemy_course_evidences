package com.eventsequor.spring_errors.controllers;

import com.eventsequor.spring_errors.exceptions.UserNotFoundException;
import com.eventsequor.spring_errors.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Error> divisionByZero(Exception exception) {
        Error error = new Error();
        error.setError("Division by zero");
        error.setDate(new Date());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> noFoundEx(Exception exception) {
        Error error = new Error();
        error.setDate(new Date());
        error.setMessage("Api no found");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> numberFormatException(Exception exception) {
        Map<String, String> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "Incorrect number");
        error.put("message", exception.getMessage());
        error.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return error;
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> nullException(Exception exception) {
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "The role does not exist");
        error.put("message", exception.getMessage());
        error.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return error;
    }
}
