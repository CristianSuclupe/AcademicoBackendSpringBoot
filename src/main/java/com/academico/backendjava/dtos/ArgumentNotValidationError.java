package com.academico.backendjava.dtos;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArgumentNotValidationError {

    private Integer statusCode;
    private HttpStatus status;
    private Map<String, String> message;
}
