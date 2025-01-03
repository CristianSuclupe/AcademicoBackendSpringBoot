package com.academico.backendjava.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.backendjava.entities.AcademicProduct;

public interface AcademicProductRepository extends JpaRepository<AcademicProduct, Long>{

    @Query("SELECT ap FROM AcademicProduct ap JOIN ap.class_ c WHERE c.id = ?1 AND ap.enable = true " +
        "AND ap.deadLine >= ?2")
    List<AcademicProduct> listByClass(Long classId, Date today);

}
