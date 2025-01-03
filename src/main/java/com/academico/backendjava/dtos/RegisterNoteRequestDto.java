package com.academico.backendjava.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterNoteRequestDto {

    @NotNull
    private Long studentId;

    @NotNull
    private Long academicProductId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "El puntaje debe ser mayor o igual a 0")
    @DecimalMax(value = "20.0", inclusive = true, message = "El puntaje debe ser menor o igual a 20")
    private Float score;

    
}
