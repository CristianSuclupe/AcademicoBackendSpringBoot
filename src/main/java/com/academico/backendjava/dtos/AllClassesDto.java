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
public class AllClassesDto {

    private Long classId;
    private String identifierName;
    private String courseName;
    private Long currentAmount;
    private Long maximunCapacity;
    private String teacherName;
    private String dni;
    private Date deadLine;
}
