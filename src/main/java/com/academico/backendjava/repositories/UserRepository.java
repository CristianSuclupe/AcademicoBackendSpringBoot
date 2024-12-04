package com.academico.backendjava.repositories;

import com.academico.backendjava.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
