package com.spring.boot.jpa.Persistence.models.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.models.program.ProgramCourseList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private ProgramCourseList programCourseList;

    @OneToOne(mappedBy = "course")
    @JsonManagedReference
    private Lecture lecture;

//    @ManyToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    @JoinTable(name = "Course_Assignment")
//    private List<Lecturer> lecturer;
    public Course(Integer id){
        this.course_id = id;
    }
}
