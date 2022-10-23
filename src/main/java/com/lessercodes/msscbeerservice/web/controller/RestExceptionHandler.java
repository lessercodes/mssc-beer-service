package com.lessercodes.msscbeerservice.web.controller;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e) {
        val errors = e.getConstraintViolations().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }
}
