package com.spring.boot.jpa.Persistence.models.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import com.spring.boot.jpa.Persistence.models.program.ProgramCourseList;
import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Department department;

    @OneToOne(mappedBy = "course")
    @JsonManagedReference
    private ProgramCourseList programCourseList;

    @OneToOne(mappedBy = "course")
    @JsonManagedReference
    private Lecture lecture;
}
