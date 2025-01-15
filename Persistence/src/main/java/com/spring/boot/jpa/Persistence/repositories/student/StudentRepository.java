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
import java.util.Optional;

public interface StudentRepository extends EntityBaseClassRepository<Student>,  JpaRepository<Student, Integer> {
    Optional<Student> findByNrcNumber(String nrc);
    Optional<Student> findByStudentNumber(String studentNumber);

    @Query(value = "SELECT s FROM Student s WHERE s.studentNumber = :studentNumber")
    Optional<Student> findByStudentNumberQuery(String studentNumber);

    @Query(value = "SELECT s FROM Student s")
    List<Student> findAllQuery();

//    //Testing
//    @Query(value = "SELECT :args FROM Student s WHERE s.studentNumber = :id")
//    Student findStudentWithCustomFields(String id, String... args);
//    @Query(value = "SELECT ...:args FROM Student")
//    List<Student> findAllStudentsWithCustomFields(String... args);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Student s WHERE s.studentNumber = :studentNumber")
    int deleteByStudentNumber(String studentNumber);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Student s WHERE s.nrcNumber = :nrc")
    int deleteByNrcNumber(String nrc);
}
