package com.academico.backendjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.academico.backendjava.services.AcademicProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/v1/api/academicproduct")
@RequiredArgsConstructor
public class AcademicProductController {

    private final AcademicProductService academicProductService;

    @GetMapping("class/{classId}")
    public ResponseEntity<?> getAcademicProductsByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(academicProductService.ListByClass(classId));
    }
    
}
