package com.academico.backendjava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.entities.Class;

public interface ClassRepository extends JpaRepository<Class, Long>{

    @Query("SELECT c FROM Class c JOIN c.teacher t WHERE t.id = ?1")
    List<Class> findByTeacherId(Long teacherId);
}
