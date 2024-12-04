package com.academico.backendjava.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(min = 8, max = 8)
    private String dni;

    @Size(min = 9, max = 9)
    private String phoneNumber;

    private String address;

    private Date birthday;

}
