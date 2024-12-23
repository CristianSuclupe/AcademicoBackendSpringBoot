package com.academico.backendjava.services;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.dtos.UserInformationDto;
import com.academico.backendjava.entities.Person;
import com.academico.backendjava.entities.Student;
import com.academico.backendjava.exceptions.HttpException;
import com.academico.backendjava.repositories.PersonRepository;
import com.academico.backendjava.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final PersonRepository personRepository;

    private final StudentRepository studentRepository;

    @Override
    public HttpResponseDto<UserInformationDto> findStudentByDni(String dni) {
        try {
            Optional<Person> optionalPerson = personRepository.findByDni(dni);
            Person person = optionalPerson.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Persona no encontrada"));
            Optional<Student> optionalStudent = studentRepository.findByPersonId(person.getPersonId());
            Student student = optionalStudent.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Persona no encontrada"));
            UserInformationDto userInformation = UserInformationDto.builder()
                .id(student.getStudentId())
                .firstName(student.getPerson().getFirstName())
                .middleName(student.getPerson().getMiddleName())
                .lastName(student.getPerson().getLastName())
                .dni(student.getPerson().getDni())
                .phoneNumber(student.getPerson().getPhoneNumber())
                .address(student.getPerson().getAddress())
                .birthday(student.getPerson().getBirthday())
                .build();
            return HttpResponseDto.<UserInformationDto>builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(userInformation)
                .build();
        }
        catch(HttpException e) {
            throw e;
        }
        catch(Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Error de servidor");
        }
    }

}
