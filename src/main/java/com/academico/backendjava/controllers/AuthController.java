package com.academico.backendjava.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("login")
    public String login() {
        return "AuthFuncionando";
    }

    @PostMapping("register")
    public String register(@RequestBody String entity) {
        return "Te has registrado";
    }
    
    
}