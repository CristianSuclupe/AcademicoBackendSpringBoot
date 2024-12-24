package com.academico.backendjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.backendjava.entities.Person;


public interface PersonRepository extends JpaRepository<Person, Long>{
    Optional<Person> findByDni(String dni);
}
