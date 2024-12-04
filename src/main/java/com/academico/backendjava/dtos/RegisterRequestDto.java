package com.academico.backendjava.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String address;
    private Date birthday;
    private String email;
    private String password;
    private String role;
    private String specialization;

}
