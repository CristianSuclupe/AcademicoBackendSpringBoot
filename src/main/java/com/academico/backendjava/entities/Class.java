package com.academico.backendjava.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Classes")
public class Class {

    @Id
    @GeneratedValue()
    private Long classId;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private Long currentAmount;

    @Column(nullable = false)
    private Long maximunCapacity;

    @Column(nullable = false)
    private Date deadLine;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "class_")
    private List<AcademicProduct> academicProducts;

    @OneToMany(mappedBy = "class_")
    private List<Register> registers;

}
