package com.spring.boot.jpa.Persistence.models.department;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.school.School;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Department {
    @Id
    private Integer id;
    private String department_name;

    @OneToOne
    @JoinColumn(name = "hod_id")
    @JsonBackReference
    private Lecturer HOD;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private School school;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Course> courses;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Program> programs;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Lecturer> lecturer;
}
