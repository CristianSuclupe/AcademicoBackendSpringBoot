package com.academico.backendjava.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.dtos.StudentPerClassProjection;
import com.academico.backendjava.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT s FROM Student s JOIN s.person p WHERE p.personId = ?1")
    Optional<Student> findByPersonId(Long personId);

    @Query("SELECT s FROM Student s JOIN s.person p WHERE p.dni = ?1")
    Optional<Student> findByDni(String dni);

    @Query(value = "SELECT s.student_id AS studentId, p.last_name AS lastName, " +
                   "CASE WHEN p.middle_name IS NOT NULL AND p.middle_name != '' THEN CONCAT(p.first_name, ' ', p.middle_name) ELSE p.first_name END AS name, " +
                   "p.dni AS dni, " +
                   "CASE WHEN rn.register_note_id IS NOT NULL THEN rn.score ELSE 0 END AS score, " +
                   "CASE WHEN rn.register_note_id IS NOT NULL THEN 1 ELSE 0 END AS existRegisterNote " +
                   "FROM persons p " +
                   "JOIN students s ON p.person_id = s.person_id " +
                   "JOIN registers r ON s.student_id = r.student_id " +
                   "JOIN classes c ON r.class_id = c.class_id " +
                   "LEFT JOIN register_notes rn ON rn.student_id = s.student_id AND rn.academic_product_id = :productAcademicId " +
                   "WHERE c.class_id = :classId ", nativeQuery = true)
    List<StudentPerClassProjection> ListStudentsPerClassAndProductAcademic(Long classId, Long productAcademicId);
}
