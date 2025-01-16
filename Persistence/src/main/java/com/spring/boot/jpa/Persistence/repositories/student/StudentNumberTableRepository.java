package com.spring.boot.jpa.Persistence.repositories.student;

import com.spring.boot.jpa.Persistence.models.student.StudentNumberTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentNumberTableRepository extends JpaRepository<StudentNumberTable, Integer> {
}
