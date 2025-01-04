package com.spring.boot.jpa.Persistence.models.department;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.models.student.Student;
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
    @Column(name = "department_name")
    private String departmentName;
    @Column(unique = true)
    private String departmentCode;

    @OneToOne
    @JoinColumn(name = "hod_id", unique = true)
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

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Student> students;

    public Department(Integer id){
        this.id = id;
    }
}
