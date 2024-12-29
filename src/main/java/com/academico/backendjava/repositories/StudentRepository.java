package com.academico.backendjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT s FROM Student s JOIN s.person p WHERE p.personId = ?1")
    Optional<Student> findByPersonId(Long personId);

    @Query("SELECT s FROM Student s JOIN s.person p WHERE p.dni = ?1")
    Optional<Student> findByDni(String dni);
}
