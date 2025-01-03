package com.academico.backendjava.services;

import java.util.List;

import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.dtos.StudentPerClassProjection;
import com.academico.backendjava.dtos.UserInformationDto;

public interface IStudentService {

    HttpResponseDto<UserInformationDto> findStudentByDni(String dni);

    HttpResponseDto<String> registerStudent(UserInformationDto data);

    HttpResponseDto<List<StudentPerClassProjection>> listStudentPerClassAndProductAcademic(Long classId, Long productAcademicId);
}
