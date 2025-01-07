package com.academico.backendjava.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.dtos.RegisterNoteRequestDto;
import com.academico.backendjava.entities.AcademicProduct;
import com.academico.backendjava.entities.RegisterNote;
import com.academico.backendjava.entities.Student;
import com.academico.backendjava.exceptions.HttpException;
import com.academico.backendjava.repositories.AcademicProductRepository;
import com.academico.backendjava.repositories.RegisterNoteRepository;
import com.academico.backendjava.repositories.StudentRepository;
import com.academico.backendjava.validators.RegisterNoteValidator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterNoteService implements IRegisterNoteService{

    private final RegisterNoteRepository registerNoteRepository;

    private final StudentRepository studentRepository;

    private final AcademicProductRepository academicProductRepository;

    private final RegisterNoteValidator registerNoteValidator;

    @Override
    @Transactional
    public HttpResponseDto<String> saveRegisterNote(List<RegisterNoteRequestDto> request) {
        try {
            Optional<AcademicProduct> optionalAcademicProduct = academicProductRepository.findById(request.get(0).getAcademicProductId());
            AcademicProduct academicProduct = optionalAcademicProduct.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Producto académico no encontrado"));
            registerNoteValidator.validarExistRegisterNote(academicProduct.getAcademicProductId());
            registerNoteValidator.validateNumberOfStudents(request, academicProduct.getClass_().getClassId());
            List<RegisterNote> registerNotes = request.stream()
            .map(aux -> {
                Optional<Student> optionalStudent = studentRepository.findById(aux.getStudentId());
                Student student = optionalStudent.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
                return RegisterNote.builder()
                    .student(student)
                    .academicProduct(academicProduct)
                    .score(aux.getScore())
                    .build();
            })
            .collect(Collectors.toList());
            registerNoteRepository.saveAll(registerNotes);
            return HttpResponseDto.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result("Se registraron las notas correctamente")
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
