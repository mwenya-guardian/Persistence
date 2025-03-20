package com.spring.boot.jpa.Persistence.models.program;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.boot.jpa.Persistence.models.course.Course;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ProgramCourseList {
    @Id
    @GeneratedValue
    private Integer id;
    @NonNull
    private String year;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    @NonNull
    private Program program;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    @NonNull
    private Course course;
}
