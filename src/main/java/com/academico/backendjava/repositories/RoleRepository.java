package com.academico.backendjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.backendjava.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}