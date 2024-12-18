package com.academico.backendjava.services;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.academico.backendjava.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import static com.academico.backendjava.security.JwtTokenConfig.*;

@Service
@RequiredArgsConstructor
public class JwtService implements IJwtService{


    @Override
    public String getToken(User user) {
        return getToken(new HashMap<>(), user);
    }
        
    private String getToken(Map<String, Object> extraClaims, User user) {
        return Jwts
            .builder()
            .claims(extraClaims)
            .claim("userId", user.getUserId())
            .claim("dni", user.getPerson().getDni())
            .claim("role", user.getRole().getName())
            .subject(user.getUsername())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            //.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
            .issuedAt(new Date(System.currentTimeMillis()))
            .signWith(SECRET_KEY)
            .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username =  getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
            .parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
