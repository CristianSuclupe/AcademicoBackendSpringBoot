package com.academico.backendjava.dtos;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseDto<T> {
    private Integer statusCode;
    private HttpStatus status;
    private T result;

    
}
