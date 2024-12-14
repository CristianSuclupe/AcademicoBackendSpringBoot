package com.academico.backendjava.services;

import java.util.List;
import com.academico.backendjava.entities.Class;
import com.academico.backendjava.dtos.ClassByTeacherDto;
import com.academico.backendjava.dtos.HttpResponseDto;

public interface IClassService {

    HttpResponseDto<List<ClassByTeacherDto>> findByTeacher(String teacherId);
}
