package com.academico.backendjava.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.backendjava.dtos.AuthResponseDto;
import com.academico.backendjava.dtos.LoginRequestDto;
import com.academico.backendjava.dtos.LoginResponseDto;
import com.academico.backendjava.dtos.RegisterRequestDto;
import com.academico.backendjava.entities.Person;
import com.academico.backendjava.entities.Role;
import com.academico.backendjava.entities.User;
import com.academico.backendjava.exceptions.HttpException;
import com.academico.backendjava.factories.RoleStrategyFactory;
import com.academico.backendjava.repositories.PersonRepository;
import com.academico.backendjava.repositories.RoleRepository;
import com.academico.backendjava.repositories.UserRepository;
import com.academico.backendjava.strategies.IRoleStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PersonRepository personRepository;

    private final RoleStrategyFactory roleStrategyFactory;


    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userRepository.findByEmail(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);
            return LoginResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .status((HttpStatus.OK))
                .token(token)
                .build();
        }
        catch(BadCredentialsException ex) {
            throw new HttpException(HttpStatus.FORBIDDEN, "Usuario y/o contraseña incorrectos");
        }
        catch(Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Error de servidor");
        }
        
    }

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto request) {

        try {
            Optional<Role> optionalRole =  roleRepository.findByName("ROLE_" + request.getRole().toUpperCase());
            Role role = optionalRole.orElseThrow(() -> new HttpException(HttpStatus.BAD_REQUEST,"Rol invalido"));
            Person person = Person.builder()
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .dni(request.getDni())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .birthday(request.getBirthday())
                .build();

            User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .person(person)
                .build();
            
            personRepository.save(person);
            userRepository.save(user);

            IRoleStrategy roleStrategy = roleStrategyFactory.getStrategy(role.getName());
            roleStrategy.createRole(person, request);

            return AuthResponseDto.builder()
                .message("Usuario registrado con exito!")
                .build();
        }
        catch(HttpException e){
            throw e;
        }
        catch(Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo registrar el usuario");
        }
    }

   
}
