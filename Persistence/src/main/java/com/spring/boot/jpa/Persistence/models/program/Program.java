package com.spring.boot.jpa.Persistence.models.program;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.school.School;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Program {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private School school;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Department department;
}
