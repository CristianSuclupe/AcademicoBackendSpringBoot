package com.academico.backendjava.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academico.backendjava.dtos.AuthResponseDto;
import com.academico.backendjava.dtos.LoginRequestDto;
import com.academico.backendjava.dtos.RegisterRequestDto;
import com.academico.backendjava.entities.Person;
import com.academico.backendjava.entities.Role;
import com.academico.backendjava.entities.User;
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

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PersonRepository personRepository;

    private final RoleStrategyFactory roleStrategyFactory;


    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto request) {

        Optional<Role> optionalRole =  roleRepository.findByName("ROLE_" + request.getRole());
        Role role = optionalRole.orElseThrow(() -> new IllegalArgumentException("Role invalido"));
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
            .password(request.getPassword())
            .role(role)
            .person(person)
            .build();
        
        personRepository.save(person);
        userRepository.save(user);

        IRoleStrategy roleStrategy = roleStrategyFactory.getStrategy(role.getName());
        roleStrategy.createRole(person, request);

        return AuthResponseDto.builder()
            .token(jwtService.getToken(user))
            .build();
    }

   
}