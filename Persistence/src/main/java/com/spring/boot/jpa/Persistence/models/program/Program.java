package com.spring.boot.jpa.Persistence.models.program;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.models.student.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Program {
    @Id
    @GeneratedValue
    private Integer program_id;
    @Column(unique = true)
    private String programCode;
    @Column(nullable = false)
    private String programName;

    @OneToMany(mappedBy = "program")
    @JsonBackReference
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

    public Program(Integer id){
        this.program_id = id;
    }
}
