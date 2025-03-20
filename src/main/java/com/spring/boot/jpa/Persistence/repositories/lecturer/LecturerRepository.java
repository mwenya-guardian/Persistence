package com.spring.boot.jpa.Persistence.repositories.lecturer;

import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.repositories.EntityBaseClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends EntityBaseClassRepository<Lecturer>, JpaRepository<Lecturer, Integer> {
    @Query(value = "SELECT l FROM Lecturer l WHERE l.nrcNumber = :nrc")
    Optional<Lecturer> findByNrcNumber(String nrc);

    @Query(value = "SELECT l FROM Lecturer l WHERE l.department = :departmentId")
    List<Lecturer> findAllByDepartmentId(String departmentId);
    List<Lecturer> findAllByDepartment(Department department);

    @Query(value = "SELECT l FROM Lecturer l WHERE l.lecturerNumber = :lectureNumber")
    Optional<Lecturer> findByLecturerNumber(@Param("lectureNumber") String lectureNumber);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Lecturer l WHERE l.nrcNumber = :nrc")
    Integer deleteByNrcNumber(String nrc);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Lecturer l WHERE l.lecturerNumber = :lectureNumber")
    Integer deleteByLecturerNumber(@Param("lectureNumber") String id);
}
