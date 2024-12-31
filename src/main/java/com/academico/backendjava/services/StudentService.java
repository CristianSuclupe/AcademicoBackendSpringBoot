package com.academico.backendjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.dtos.StudentPerClassProjection;
import com.academico.backendjava.dtos.UserInformationDto;
import com.academico.backendjava.entities.Person;
import com.academico.backendjava.entities.Student;
import com.academico.backendjava.exceptions.HttpException;
import com.academico.backendjava.repositories.PersonRepository;
import com.academico.backendjava.repositories.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final PersonRepository personRepository;

    private final StudentRepository studentRepository;

    @Override
    public HttpResponseDto<UserInformationDto> findStudentByDni(String dni) {
        try {
            if(dni == null || dni.isEmpty()) throw new HttpException(HttpStatus.BAD_REQUEST, "No se a enviado DNI");
            Optional<Person> optionalPerson = personRepository.findByDni(dni);
            Person person = optionalPerson.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "No se encontró al estudiante en el sistema"));
            Optional<Student> optionalStudent = studentRepository.findByPersonId(person.getPersonId());
            Student student = optionalStudent.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "No se encontró al estudiante en el sistema"));
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
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public HttpResponseDto<String> registerStudent(UserInformationDto data) {
        try {
            Person person = Person.builder()
                .dni(data.getDni())
                .firstName(data.getFirstName())
                .middleName(data.getMiddleName())
                .lastName(data.getLastName())
                .address(data.getAddress())
                .phoneNumber(data.getPhoneNumber())
                .birthday(data.getBirthday())
                .build();
            
            Student student = Student.builder()
                .person(person)
                .build();
            
            personRepository.save(person);
            studentRepository.save(student);

            return HttpResponseDto.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result("Estudiante registrado")
                .build();
        }
        catch(HttpException e) {
            throw e;
        }
        catch(Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public HttpResponseDto<List<StudentPerClassProjection>> listStudentPerClass(Long classId) {
        try {
            List<StudentPerClassProjection> studentList = studentRepository.ListStudentsPerClass(classId);
            return HttpResponseDto.<List<StudentPerClassProjection>>builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(studentList)
                .build();
        }
        catch(HttpException e) {
            throw e;
        }
        catch(Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    

}
