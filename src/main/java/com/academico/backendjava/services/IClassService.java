package com.academico.backendjava.services;

import java.util.List;

import com.academico.backendjava.dtos.AllClassesDto;
import com.academico.backendjava.dtos.ClassByTeacherDto;
import com.academico.backendjava.dtos.HttpResponseDto;

public interface IClassService {

    HttpResponseDto<List<ClassByTeacherDto>> findByTeacher(String teacherId);

    HttpResponseDto<List<AllClassesDto>> findAllClases(String courseName);
}
