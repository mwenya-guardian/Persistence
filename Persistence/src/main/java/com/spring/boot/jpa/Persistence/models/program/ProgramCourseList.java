package com.spring.boot.jpa.Persistence.models.program;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.boot.jpa.Persistence.models.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProgramCourseList {
    @Id
    @GeneratedValue
    private Integer id;
    private String year;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Program program;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Course course;
}
