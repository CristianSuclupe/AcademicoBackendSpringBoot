package com.academico.backendjava.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.academico.backendjava.dtos.ArgumentNotValidationError;
import com.academico.backendjava.dtos.ErrorDto;
import com.academico.backendjava.exceptions.HttpException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorDto> httpExceptionHandler(HttpException ex) {
        ErrorDto error = ErrorDto.builder()
                            .status(ex.getStatus())
                            .statusCode(ex.getStatus().value())
                            .message(ex.getMessage())
                            .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentNotValidationError> validationExcepcionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> 
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));

        ArgumentNotValidationError error = ArgumentNotValidationError.builder()
                                            .status(HttpStatus.BAD_REQUEST)
                                            .statusCode(HttpStatus.BAD_REQUEST.value())
                                            .message(errors)
                                            .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
