package com.academico.backendjava.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterInAClassRequestDto {

    @NotNull
    private Long classId;

    @NotBlank
    private String secretaryDni;

    @NotBlank
    private String studentDni;
}
