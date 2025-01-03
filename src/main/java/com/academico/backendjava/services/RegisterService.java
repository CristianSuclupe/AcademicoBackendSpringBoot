package com.academico.backendjava.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.dtos.RegisterInAClassRequestDto;
import com.academico.backendjava.entities.Class;
import com.academico.backendjava.entities.Register;
import com.academico.backendjava.entities.Secretary;
import com.academico.backendjava.entities.Student;
import com.academico.backendjava.exceptions.HttpException;
import com.academico.backendjava.repositories.ClassRepository;
import com.academico.backendjava.repositories.RegisterRepository;
import com.academico.backendjava.repositories.SecretaryRepository;
import com.academico.backendjava.repositories.StudentRepository;
import com.academico.backendjava.validators.ClassValidator;
import com.academico.backendjava.validators.RegisterValidator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService implements IRegisterService {

    private final RegisterRepository registerRepository;

    private final StudentRepository studentRepository;

    private final SecretaryRepository secretaryRepository;

    private final ClassRepository classRepository;

    private final ClassValidator classValidator;

    private final RegisterValidator registerValidator;

    @Override
    @Transactional
    public HttpResponseDto<String> registerStudentInAClass(RegisterInAClassRequestDto request) {
        try {
            Optional<Class> classOptional = classRepository.findById(request.getClassId());
            Class class1 = classOptional.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "No se encontró la clase seleccionada"));
            Optional<Secretary> secretaryOptional = secretaryRepository.findByDni(request.getSecretaryDni());
            Secretary secretary = secretaryOptional.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "No se encontró al secretario"));
            Optional<Student> studentOptional = studentRepository.findByDni(request.getStudentDni());
            Student student = studentOptional.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "No se encontró al estudiante"));
            registerValidator.validateDuplicateStudentRegisterInClass(student.getStudentId(), class1.getClassId());
            classValidator.validateMaxStudents(class1);
            classValidator.validateInscriptionDate(class1);
            Register register = Register.builder()
                .class_(class1)
                .student(student)
                .secretary(secretary)
                .build();
            registerRepository.save(register);
            // class1.setCurrentAmount(class1.getCurrentAmount() + 1);
            // classRepository.save(class1);
            return HttpResponseDto.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result("Se matriculo al alumno correctamente")
                .build();
        }
        catch(HttpException e){
            throw e;
        }
        catch(Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
