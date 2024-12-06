package com.academico.backendjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
