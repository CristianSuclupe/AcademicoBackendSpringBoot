package com.academico.backendjava.dtos;

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
    private String birthday;
}
