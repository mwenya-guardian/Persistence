package com.spring.boot.jpa.Persistence.repository.student;

import com.spring.boot.jpa.Persistence.models.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
