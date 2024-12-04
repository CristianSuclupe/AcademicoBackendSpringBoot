package com.academico.backendjava.strategies;

import org.springframework.stereotype.Service;

import com.academico.backendjava.dtos.RegisterRequestDto;
import com.academico.backendjava.entities.Person;
import com.academico.backendjava.entities.Secretary;
import com.academico.backendjava.repositories.SecretaryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecretaryRoleStrategy implements IRoleStrategy{

    private final SecretaryRepository secretaryRepository;
    
    @Override
    public void createRole(Person person, RegisterRequestDto request) {
        Secretary secretary = Secretary.builder()
            .person(person)
            .build();
        secretaryRepository.save(secretary);
    }

    @Override
    public String getRoleName() {
        return "ROLE_SECRETARY";
    }

}
