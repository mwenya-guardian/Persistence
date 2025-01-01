package com.spring.boot.jpa.Persistence.repositories.course;

import com.spring.boot.jpa.Persistence.models.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAllByDepartment(Integer departmentId);
    Course findByCourseCode(String courseCode);
    void deleteByCourseCode(String courseCode);
    List<Course> findAllByCourseNameContaining(String name);
}

