package com.academico.backendjava.services;

import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.dtos.RegisterInAClassRequestDto;

public interface IRegisterService {

    HttpResponseDto<String> registerStudentInAClass(RegisterInAClassRequestDto request);
}
