package com.spring.boot.jpa.Persistence.models.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.boot.jpa.Persistence.models.school.School;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
@Data
@Entity
@NoArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"age"}))
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String nrc_number;
    @Column(updatable = false)
    private String student_id;
    private String firstname;
    private String lastname;
    private String address;
    private Date dob;
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private School school;
}
