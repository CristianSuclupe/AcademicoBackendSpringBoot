package com.academico.backendjava.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.entities.RegisterNote;

public interface RegisterNoteRepository extends JpaRepository<RegisterNote, UUID>{

    @Query("SELECT rn FROM RegisterNote rn JOIN rn.academicProduct ac WHERE ac.id = ?1")
    List<RegisterNote> findByAcademicProduct(Long academicProductId);

}
