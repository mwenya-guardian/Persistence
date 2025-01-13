package com.spring.boot.jpa.Persistence.models.program;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.models.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue
    private Integer programId;
    @Column(unique = true)
    private String programCode;
    @Column(nullable = false)
    private String programName;
    private String Duration;

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
    
    @OneToMany(mappedBy = "program", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ProgramCourseList> programCourseList;

    public Program(Integer id){
        this.programId = id;
    }
}
