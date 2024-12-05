package com.academico.backendjava.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/v1/api/test")
@RequiredArgsConstructor
public class TestController {

    @PostMapping
    public String test() {
        return "validado por token";
    }
    
}
