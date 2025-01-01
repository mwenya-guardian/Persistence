package com.spring.boot.jpa.Persistence.repositories.student;
    
import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.EntityBaseClassRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends EntityBaseClassRepository<Student>,  JpaRepository<Student, Integer> {
    Student findByStudentId(String studentId);
}
