package com.academico.backendjava.services;

import org.springframework.security.core.userdetails.UserDetails;


public interface IJwtService {
    String getToken(UserDetails user);
}
