package com.spring.boot.jpa.Persistence.repositories.course;

import com.spring.boot.jpa.Persistence.models.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    CourseRepository extends JpaRepository<Course, Integer> {
}
