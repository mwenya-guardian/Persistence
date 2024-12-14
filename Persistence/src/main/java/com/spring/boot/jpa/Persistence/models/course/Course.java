package com.spring.boot.jpa.Persistence.models.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Department department;
}
