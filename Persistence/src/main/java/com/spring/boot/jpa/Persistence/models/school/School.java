package com.spring.boot.jpa.Persistence.models.school;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.student.Student;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue
    private Integer school_id;
    @Column(unique = true)
    private String Name;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private List<Student> students;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private List<Department> departments;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private List<Program> programs;
}
