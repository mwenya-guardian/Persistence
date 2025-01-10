package com.spring.boot.jpa.Persistence.repositories.student;
    
import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.EntityBaseClassRepository;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.BatchSize;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends EntityBaseClassRepository<Student>,  JpaRepository<Student, Integer> {
    Student findByNrcNumber(String nrc);
    Student findByStudentNumber(String studentNumber);

    @Query(value = "SELECT s FROM Student s")
    List<Student> findAllQuery();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Student s where s.studentNumber = :studentNumber")
    int deleteByStudentNumber(String studentNumber);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Student s WHERE s.nrcNumber = :nrc")
    int deleteByNrcNumber(String nrc);
}
