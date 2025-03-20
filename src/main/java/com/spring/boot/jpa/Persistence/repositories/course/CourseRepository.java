package com.spring.boot.jpa.Persistence.repositories.course;

import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.department.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT c FROM Course c WHERE c.department.id = :departmentId")
    List<Course> findAllByDepartment(Integer departmentId);
    @Query(value = "SELECT c FROM Course c WHERE c.courseCode = :courseCode")
    Optional<Course> findByCourseCode(String courseCode);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Course c WHERE c.courseCode = :courseCode")
    Integer deleteByCourseCode(String courseCode);
    List<Course> findAllByCourseNameContaining(String name);
}

