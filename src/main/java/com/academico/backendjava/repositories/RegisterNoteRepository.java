package com.academico.backendjava.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academico.backendjava.entities.RegisterNote;

public interface RegisterNoteRepository extends JpaRepository<RegisterNote, UUID>{

}
