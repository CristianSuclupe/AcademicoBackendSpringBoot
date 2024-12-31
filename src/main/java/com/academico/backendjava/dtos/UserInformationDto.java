package com.academico.backendjava.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Size(min = 8, max = 8)
    private String dni;
    @Size(min = 9, max = 9)
    private String phoneNumber;
    private String address;
    @NotNull
    private Date birthday;
}
