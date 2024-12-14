package com.spring.boot.jpa.Persistence.models.program;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.models.student.Student;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Program {
    @Id
    private Integer id;
    @Column(nullable = false)
    private String programName;

    @OneToOne(mappedBy = "program")
    @JsonManagedReference
    private ProgramCourseList programCourseList;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private School school;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Department department;

    @OneToMany(mappedBy = "program")
    @JsonManagedReference
    private List<Student> students;
}
