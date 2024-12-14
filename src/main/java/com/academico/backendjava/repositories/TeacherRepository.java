package com.academico.backendjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

    @Query("SELECT t FROM Teacher t JOIN t.person p WHERE p.id = ?1")
    Optional<Teacher> findByPersonId(Long personId);
}
