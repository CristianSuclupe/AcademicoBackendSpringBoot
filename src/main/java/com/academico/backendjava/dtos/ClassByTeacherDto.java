package com.academico.backendjava.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassByTeacherDto {

    private Long classId;
    private String courseName;
    private String identifierName;
    private Long teacherId;

}
