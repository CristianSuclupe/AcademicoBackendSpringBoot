package com.academico.backendjava.services;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import static com.academico.backendjava.security.JwtTokenConfig.*;

@Service
@RequiredArgsConstructor
public class JwtService implements IJwtService{


    @Override
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }
        
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(user.getUsername())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .issuedAt(new Date(System.currentTimeMillis()))
            .signWith(SECRET_KEY)
            .compact();
    }


}
