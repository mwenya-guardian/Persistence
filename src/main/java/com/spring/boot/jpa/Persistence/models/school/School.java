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
    private String schoolCode;
    @Column(unique = true)
    private String schoolName;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private List<Student> students;

    @OneToMany(mappedBy = "school", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Department> departments;

    @OneToMany(mappedBy = "school", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Program> programs;

    public School(Integer id){
        this.school_id = id;
    }
}
