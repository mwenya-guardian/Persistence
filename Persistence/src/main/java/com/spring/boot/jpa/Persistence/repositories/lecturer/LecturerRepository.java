package com.spring.boot.jpa.Persistence.repositories.lecturer;

import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.repositories.EntityBaseClassRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends EntityBaseClassRepository<Lecturer>, JpaRepository<Lecturer, Integer> {
}
