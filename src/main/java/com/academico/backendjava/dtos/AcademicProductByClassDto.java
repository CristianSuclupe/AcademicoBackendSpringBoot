package com.academico.backendjava.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicProductByClassDto {

    private Long academicProductId;
    private Date deadLine;
    private String name;
    private Float percentage;
}
