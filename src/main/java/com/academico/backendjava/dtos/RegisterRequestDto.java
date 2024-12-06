package com.academico.backendjava.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String dni;
    private String phoneNumber;
    private String address;
    @NotNull
    private Date birthday;
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String role;
    private String specialization;

}
