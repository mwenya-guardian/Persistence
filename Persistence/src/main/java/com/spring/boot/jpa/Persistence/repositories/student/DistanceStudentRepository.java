package com.spring.boot.jpa.Persistence.repositories.student;

import com.spring.boot.jpa.Persistence.models.student.DistanceStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanceStudentRepository extends JpaRepository<DistanceStudent, Integer> {
}
