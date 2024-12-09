package com.academico.backendjava.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AcademicProducts")
public class AcademicProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long academicProductId;

    @Column(nullable = false)
    private Date deadLine;

    @Column(nullable = false)
    private String name;

    private Float percentage;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class class_;

    @OneToMany(mappedBy = "academicProduct")
    private List<RegisterNote> registerNotes;

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
