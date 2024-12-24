package com.academico.backendjava.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationDto {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String address;
    private Date birthday;
}
