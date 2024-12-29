package com.academico.backendjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.entities.Secretary;

public interface SecretaryRepository extends JpaRepository<Secretary, Long>{

    @Query("SELECT s FROM Secretary s JOIN s.person p WHERE p.dni = ?1")
    Optional<Secretary> findByDni(String dni);
}
