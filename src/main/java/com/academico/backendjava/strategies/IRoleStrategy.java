package com.academico.backendjava.strategies;

import com.academico.backendjava.dtos.RegisterRequestDto;
import com.academico.backendjava.entities.Person;

public interface IRoleStrategy {
    void createRole(Person person, RegisterRequestDto request);
    String getRoleName();
}
