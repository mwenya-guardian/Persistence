package com.spring.boot.jpa.Persistence.models.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.boot.jpa.Persistence.models.EntityBaseClass;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.school.School;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.core.annotation.AliasFor;

import java.sql.Date;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"age"}))
public class Student extends EntityBaseClass {
//    @Id
//    @GeneratedValue
//    private Integer id;
    @Column(unique = true, name = "NRC_Number", nullable = false)
    private String nrcNumber;
    @Column(unique = true, name = "student_number")
    private String studentNumber;

    //@Generated
    //@CreationTimestamp
//    @PrePersist
//    void onCreate(){
//        enrollmentDate = LocalDateTime.now();
//    }
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonBackReference
    private Program program;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

}
