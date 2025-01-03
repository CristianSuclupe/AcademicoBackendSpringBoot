package com.academico.backendjava.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.academico.backendjava.dtos.RegisterNoteRequestDto;
import com.academico.backendjava.services.RegisterNoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/v1/api/registernote")
@RequiredArgsConstructor
@Validated
public class RegisterNoteController {

    private final RegisterNoteService registerNoteService;

    @PostMapping("")
    public ResponseEntity<?> registerNote(@Valid @RequestBody List<@Valid RegisterNoteRequestDto> request) {
        return ResponseEntity.ok(registerNoteService.saveRegisterNote(request));
    }
    
}
