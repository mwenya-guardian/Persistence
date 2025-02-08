package com.spring.boot.jpa.Persistence.models.lecturer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.EntityBaseClass;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer extends EntityBaseClass {
//    @Id
//    @GeneratedValue
//    private Integer id;
    @Column(unique = true, name = "nrc_number")
    private String nrcNumber;
    @Column(unique = true, updatable = false, name = "lecturer_id")
    private String lecturerNumber;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime employmentDate;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Department department;

    @OneToOne(mappedBy = "hod")
    @JsonManagedReference
    private Department departmentHead;

    @OneToMany(mappedBy = "lecturer")
    @JsonManagedReference
    private List<Lecture> lecture;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Course_Assignment",
    joinColumns = @JoinColumn(name = "lecturer", referencedColumnName = "lecturer_id"),
    inverseJoinColumns = @JoinColumn(name = "course_assigned", referencedColumnName = "course_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"course_assigned"})})
    private List<Course> course;

    public Lecturer(Integer id){
        this.setId(id);
    }

}
