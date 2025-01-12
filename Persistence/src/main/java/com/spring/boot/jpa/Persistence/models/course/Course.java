package com.spring.boot.jpa.Persistence.models.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import com.spring.boot.jpa.Persistence.models.program.ProgramCourseList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Integer courseId;
    @Column(unique = true)
    private String courseCode;
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<ProgramCourseList> programCourseList;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<Lecture> lecture;

    public Boolean isEqual(@org.jetbrains.annotations.NotNull Course course){
        if(this.courseCode.equals(
                course.getCourseCode())
        ){
            if(this.courseName.equals(
                    course.getCourseName())
            ){
                return this.department.getId()
                        .equals(course.getDepartment().getId());
            }
            else return false;
        }
        else return false;
    }
//    @ManyToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    @JoinTable(name = "Course_Assignment")
//    private List<Lecturer> lecturer;
    public Course(Integer id){
        this.courseId = id;
    }
}
