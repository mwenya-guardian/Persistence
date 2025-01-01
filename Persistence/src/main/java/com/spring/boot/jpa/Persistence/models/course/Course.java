package com.spring.boot.jpa.Persistence.models.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import com.spring.boot.jpa.Persistence.models.program.ProgramCourseList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private Integer course_id;
    @Column(unique = true)
    private String courseCode;
    private String courseName;

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
