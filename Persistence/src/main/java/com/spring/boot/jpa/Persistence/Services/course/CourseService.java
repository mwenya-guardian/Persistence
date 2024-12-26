package com.spring.boot.jpa.Persistence.Services.course;

import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.repositories.course.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;

    public Course createCourse(Course newCourse){
        return courseRepository.save(newCourse);
    }

    public void deleteCourse(Integer courseId){
        courseRepository.deleteById(courseId);
    }
    public List<Course> findAllCoursesByDepartment(Integer id){
        return courseRepository.findAllByDepartment(id);
    }
//    public List<Course> findAllCoursesBySchool(){
//        return courseRepository.findAll();
//    }
//    public List<Course> findAllCoursesByProgram(){
//        return courseRepository.findAll();
//    }
}
