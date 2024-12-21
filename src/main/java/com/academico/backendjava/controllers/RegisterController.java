package com.academico.backendjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.academico.backendjava.dtos.RegisterInAClassRequestDto;
import com.academico.backendjava.services.RegisterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/v1/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("")
    public ResponseEntity<?> registerStudentInAClass(@Valid @RequestBody RegisterInAClassRequestDto request) {
        return ResponseEntity.ok(registerService.registerStudentInAClass(request));
    }
    
}
