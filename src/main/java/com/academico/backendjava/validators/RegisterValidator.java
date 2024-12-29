package com.academico.backendjava.validators;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.academico.backendjava.entities.Register;
import com.academico.backendjava.exceptions.HttpException;
import com.academico.backendjava.repositories.RegisterRepository;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class RegisterValidator {

    private final RegisterRepository registerRepository;

    public void validateDuplicateStudentRegisterInClass(Long studentId, Long classId) {
        Optional<Register> optionalRegister = registerRepository.findStundentInClass(studentId, classId);
        if(optionalRegister.isPresent()) throw new HttpException(HttpStatus.BAD_REQUEST, "El estudiante ya se encuentra registrado en esta clase");
    }
}
