package com.academico.backendjava.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.academico.backendjava.dtos.AllClassesDto;
import com.academico.backendjava.dtos.ClassByTeacherDto;
import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.entities.Class;
import com.academico.backendjava.entities.Teacher;
import com.academico.backendjava.entities.User;
import com.academico.backendjava.exceptions.HttpException;
import com.academico.backendjava.repositories.ClassRepository;
import com.academico.backendjava.repositories.TeacherRepository;
import com.academico.backendjava.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassService implements IClassService{

    private final ClassRepository classRepository;

    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;


    @Override
    public HttpResponseDto<List<ClassByTeacherDto>> findByTeacher(String teacherId) {
        try {
            UUID id = UUID.fromString(teacherId);
            Optional<User> userOptional = userRepository.findById(id);
            if(userOptional.isEmpty()) throw new HttpException(HttpStatus.NOT_FOUND, "No se encontró al usuario");
            User user = userOptional.orElseThrow();
            Optional<Teacher> teacherOptional = teacherRepository.findByPersonId(user.getPerson().getPersonId());
            if(teacherOptional.isEmpty()) throw new HttpException(HttpStatus.NOT_FOUND, "No se encontró al profesor");
            Teacher teacher = teacherOptional.orElseThrow();
            List<Class> classes = classRepository.findByTeacherId(teacher.getTeacherId());
            if(classes.isEmpty()) throw new HttpException(HttpStatus.BAD_REQUEST, "No se encontraron clases para este profesor");
            List<ClassByTeacherDto> result = classes.stream()
                .map(aux -> ClassByTeacherDto.builder()
                    .classId(aux.getClassId())
                    .courseName(aux.getCourse().getName())
                    .identifierName(aux.getIdentifierName())
                    .teacherId(aux.getTeacher().getTeacherId())
                    .build())
                .toList();
            return HttpResponseDto.<List<ClassByTeacherDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(result)
                .build();
        }
        catch (HttpException e) {
            throw e;
        }
        catch (Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public HttpResponseDto<List<AllClassesDto>> findAllClases(String courseName) {
        try {
            List<Class> classes = classRepository.findAllClassesEnable(new Date(), courseName);
            if(classes.isEmpty()) throw new HttpException(HttpStatus.NOT_FOUND, "No se encontraron clases disponibles");
            List<AllClassesDto> result = classes.stream()
                .map(aux -> AllClassesDto.builder()
                    .classId(aux.getClassId())
                    .identifierName(aux.getIdentifierName())
                    .courseName(aux.getCourse().getName())
                    .currentAmount(aux.getCurrentAmount())
                    .maximunCapacity(aux.getMaximunCapacity())
                    .teacherName(aux.getTeacher().getPerson().getFirstName() +" "+aux.getTeacher().getPerson().getLastName())
                    .deadLine(aux.getDeadLine())
                    .dni(aux.getTeacher().getPerson().getDni())
                    .build())
                .toList();
                return HttpResponseDto.<List<AllClassesDto>>builder()
                    .statusCode(HttpStatus.OK.value())
                    .status(HttpStatus.OK)
                    .result(result)
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
