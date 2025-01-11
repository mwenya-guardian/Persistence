package com.spring.boot.jpa.Persistence.models.lecture;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue
    private Integer lectureId;
    private String lectureCode;
    private Integer duration;
    private Timestamp startTime;
    private Timestamp endTime;

    @ManyToOne
    @JoinColumn(name = "course")
    @JsonBackReference
    private Course course;

    @ManyToOne
    @JoinColumn(name = "lecturer")
    @JsonBackReference
    private Lecturer lecturer;
}
