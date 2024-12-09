package com.academico.backendjava.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RegisterNotes")
public class RegisterNote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID registerNoteId;

    @Column(nullable = false)
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "El puntaje debe ser mayor o igual a 0")
    @DecimalMax(value = "20.0", inclusive = true, message = "El puntaje debe ser menor o igual a 20")
    private Float score;

    @ManyToOne
    @JoinColumn(name = "academic_product_id", nullable = false)
    private AcademicProduct academicProduct;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private boolean enable;

    private Date createdAt;

    private Date updateAt;

    @PrePersist
    protected void onCreate() {
        enable = true;
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = new Date();
    }
}
