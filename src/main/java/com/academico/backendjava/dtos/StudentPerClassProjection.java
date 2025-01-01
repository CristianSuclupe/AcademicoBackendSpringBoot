package com.academico.backendjava.dtos;

public interface StudentPerClassProjection {
    Long getStudentId();
    String getLastName();
    String getName();
    String getDni();
    Float getScore();
    Integer getExistRegisterNote();
}
