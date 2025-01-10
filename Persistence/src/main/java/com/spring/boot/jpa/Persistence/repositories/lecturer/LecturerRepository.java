package com.spring.boot.jpa.Persistence.repositories.lecturer;

import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.repositories.EntityBaseClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends EntityBaseClassRepository<Lecturer>, JpaRepository<Lecturer, Integer> {
    Optional<Lecturer> findByNrcNumber(String nrc);
    @Query(value = "SELECT l FROM lecturer WHERE l.department = :departmentId")
    List<Lecturer> findAllByDepartment(String departmentId);
    List<Lecturer> findAllByDepartment(Department department);
    @Query(value = "SELECT l FROM lecturer WHERE l.lecturerId = :id")
    Optional<Lecturer> findByLecturerId(String id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Lecturer l WHERE l.nrcNumber = :nrc")
    Integer deleteByNrcNumber(String nrc);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Lecturer l WHERE l.lecturerId = :id")
    Integer deleteByLecturerId(String id);
}
