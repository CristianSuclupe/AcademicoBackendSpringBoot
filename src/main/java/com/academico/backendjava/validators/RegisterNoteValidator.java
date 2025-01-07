package com.academico.backendjava.validators;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.academico.backendjava.dtos.RegisterNoteRequestDto;
import com.academico.backendjava.repositories.ClassRepository;
import com.academico.backendjava.repositories.RegisterNoteRepository;
import com.academico.backendjava.entities.Class;
import com.academico.backendjava.entities.RegisterNote;
import com.academico.backendjava.exceptions.HttpException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegisterNoteValidator {

    private final ClassRepository classRepository;
    private final RegisterNoteRepository registerNoteRepository;

    public void validateNumberOfStudents(List<RegisterNoteRequestDto> list, Long classId) {
        Optional<Class> optionalClass = classRepository.findById(classId);
        Class class1 = optionalClass.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "No se encontró la clase"));
        if(list.size() != class1.getCurrentAmount()) throw new HttpException(HttpStatus.BAD_REQUEST, "Debe registrar notas a todos los estudiantes de esta clase");
    }

    public void validarExistRegisterNote(Long academicProductId) {
        List<RegisterNote> optionalRegisterNote = registerNoteRepository.findByAcademicProduct(academicProductId);
        if(optionalRegisterNote.size() > 0) throw new HttpException(HttpStatus.BAD_REQUEST, "Ya se registraron notas para este producto académico");  
    }
}
