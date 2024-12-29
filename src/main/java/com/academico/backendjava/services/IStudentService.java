package com.academico.backendjava.services;

import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.dtos.UserInformationDto;

public interface IStudentService {

    HttpResponseDto<UserInformationDto> findStudentByDni(String dni);

    HttpResponseDto<String> registerStudent(UserInformationDto data);
}
