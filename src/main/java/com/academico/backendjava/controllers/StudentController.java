package com.academico.backendjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.academico.backendjava.dtos.UserInformationDto;
import com.academico.backendjava.services.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/v1/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("{studentDni}")
    public ResponseEntity<?> getStudentByDni(@PathVariable String studentDni) {
        return ResponseEntity.ok(studentService.findStudentByDni(studentDni));
    }

    @PostMapping("")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody UserInformationDto request) {
        return ResponseEntity.ok(studentService.registerStudent(request));
    }

    @GetMapping("class/{classId}")
    public ResponseEntity<?> listStudentPerClass(@PathVariable Long classId) {
        return ResponseEntity.ok(studentService.listStudentPerClass(classId));
    }
    
    
    
}
