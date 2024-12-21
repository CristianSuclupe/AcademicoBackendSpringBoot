package com.academico.backendjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.academico.backendjava.services.ClassService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("/v1/api/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<?> getClassesByTeacher(@PathVariable String teacherId) {
        return ResponseEntity.ok(classService.findByTeacher(teacherId));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllEnableClasses() {
        return ResponseEntity.ok(classService.findAllClases());
    }
    
    

}
