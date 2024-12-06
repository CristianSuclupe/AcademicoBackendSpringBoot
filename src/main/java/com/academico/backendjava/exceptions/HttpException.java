package com.academico.backendjava.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HttpException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
