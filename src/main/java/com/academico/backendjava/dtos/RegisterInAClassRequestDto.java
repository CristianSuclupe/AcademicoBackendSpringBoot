package com.academico.backendjava.dtos;

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

    @NotNull
    private Long secretaryId;

    @NotNull
    private Long studentId;
}
