package com.academico.backendjava.dtos;


import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    
    private Integer statusCode;
    private HttpStatus status;
    private String message;

}
