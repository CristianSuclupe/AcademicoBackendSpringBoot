package com.academico.backendjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.backendjava.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
