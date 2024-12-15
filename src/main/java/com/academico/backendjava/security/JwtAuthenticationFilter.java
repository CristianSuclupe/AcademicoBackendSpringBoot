package com.academico.backendjava.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.academico.backendjava.services.JwtService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            try {
                final String token = getTokenFromRequest(request);
                final String username;
                if(token == null) {
                    filterChain.doFilter(request, response);
                    //setErrorResponse(HttpStatus.FORBIDDEN, response, "No tiene autorización para realizar esta acción");
                    return;
                }
                username = jwtService.getUsernameFromToken(token);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if(jwtService.isTokenValid(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, 
                            null, 
                            userDetails.getAuthorities()
                        );

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filterChain.doFilter(request, response);
            }
            catch(JwtException e) {
                setErrorResponse(HttpStatus.UNAUTHORIZED, response, "El token no es inválido o ha expirado");
            }
            
    }
            
    private String getTokenFromRequest(HttpServletRequest request) {
        
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) return authHeader.substring(7);
        return null;
    }

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Crear el cuerpo de la respuesta personalizada
        String jsonResponse = String.format(
            "{\"statusCode\": %d, \"status\": \"%s\", \"message\": \"%s\"}",
            status.value(), status.getReasonPhrase(), message
        );

        response.getWriter().write(jsonResponse);
    }

}
