package com.spring.boot.jpa.Persistence.repositories.lecturer;

import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
}
