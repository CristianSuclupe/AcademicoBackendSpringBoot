package com.academico.backendjava.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.backendjava.entities.Register;

public interface RegisterRepository extends JpaRepository<Register, UUID>{

    
}
