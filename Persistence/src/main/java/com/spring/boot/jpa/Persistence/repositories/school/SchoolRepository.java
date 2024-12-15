package com.spring.boot.jpa.Persistence.repositories.school;

import com.spring.boot.jpa.Persistence.models.school.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
