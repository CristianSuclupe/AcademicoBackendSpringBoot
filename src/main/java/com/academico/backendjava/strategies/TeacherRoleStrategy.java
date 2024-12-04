package com.academico.backendjava.strategies;

import org.springframework.stereotype.Service;

import com.academico.backendjava.dtos.RegisterRequestDto;
import com.academico.backendjava.entities.Person;
import com.academico.backendjava.entities.Teacher;
import com.academico.backendjava.repositories.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherRoleStrategy implements IRoleStrategy{

    private final TeacherRepository teacherRepository;

    @Override
    public void createRole(Person person, RegisterRequestDto request) {
        if(request.getSpecialization() == null && request.getSpecialization().isEmpty()){
            throw new IllegalArgumentException("La especialización es requerida para registrar a un profesor.");
        }
        Teacher teacher = Teacher.builder()
            .person(person)
            .specialization(request.getSpecialization())
            .build();
        teacherRepository.save(teacher);
    }

}
