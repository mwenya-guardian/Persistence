package com.spring.boot.jpa.Persistence.models.lecturer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
public class Lecturer {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String nrc_number;
    @Column(unique = true, updatable = false)
    private String lecture_id;
    private String firstname;
    private String lastname;
    private String address;
    private Date dob;
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Department department;

    @OneToOne(mappedBy = "HOD")
    @JsonManagedReference
    private Department department_head;

    @OneToOne(mappedBy = "lecturer")
    @JsonManagedReference
    private Lecture lecture;

}
