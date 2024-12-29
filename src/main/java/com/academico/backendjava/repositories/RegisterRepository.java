package com.academico.backendjava.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.entities.Register;

public interface RegisterRepository extends JpaRepository<Register, UUID>{

    @Query("SELECT r FROM Register r JOIN r.class_ c JOIN r.student s " +
            "WHERE s.id = :studentId AND c.id = :classId ")
    Optional<Register> findStundentInClass(Long studentId, Long classId);
}
