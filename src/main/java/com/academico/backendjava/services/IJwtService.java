package com.academico.backendjava.services;
import com.academico.backendjava.entities.User;

public interface IJwtService {
    String getToken(User user);
}
