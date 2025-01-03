package com.academico.backendjava.services;

import java.util.List;

import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.dtos.RegisterNoteRequestDto;

public interface IRegisterNoteService {

    HttpResponseDto<String> saveRegisterNote(List<RegisterNoteRequestDto> request);
}
