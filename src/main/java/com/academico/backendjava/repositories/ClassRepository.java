package com.academico.backendjava.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.entities.Class;

public interface ClassRepository extends JpaRepository<Class, Long>{

    @Query("SELECT c FROM Class c JOIN c.teacher t WHERE t.id = ?1")
    List<Class> findByTeacherId(Long teacherId);

    @Query("SELECT c FROM Class c WHERE c.maximunCapacity > c.currentAmount AND c.deadLine >= :today " +
           "AND (:courseName IS NULL OR c.course.name LIKE %:courseName%)")
    List<Class> findAllClassesEnable(Date today, String courseName);
}
