package com.academico.backendjava.services;

import com.academico.backendjava.dtos.AuthResponseDto;
import com.academico.backendjava.dtos.LoginRequestDto;
import com.academico.backendjava.dtos.RegisterRequestDto;

public interface IAuthService {

    AuthResponseDto login(LoginRequestDto request);

    AuthResponseDto register(RegisterRequestDto request);
}
