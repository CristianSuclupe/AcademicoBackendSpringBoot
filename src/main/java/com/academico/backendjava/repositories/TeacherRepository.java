package com.academico.backendjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.backendjava.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
