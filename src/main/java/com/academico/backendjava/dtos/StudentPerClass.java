package com.academico.backendjava.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentPerClass {

    private Long student_id;
    private String last_name;
    private String name;
    private String dni;
    private Float score;
}
