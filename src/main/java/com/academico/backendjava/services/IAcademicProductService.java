package com.academico.backendjava.services;

import java.util.List;

import com.academico.backendjava.dtos.AcademicProductByClassDto;
import com.academico.backendjava.dtos.HttpResponseDto;

public interface IAcademicProductService {

    HttpResponseDto<List<AcademicProductByClassDto>> ListByClass(Long classId);
}
