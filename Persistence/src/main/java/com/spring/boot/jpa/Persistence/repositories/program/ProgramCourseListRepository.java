package com.spring.boot.jpa.Persistence.repositories.program;

import com.spring.boot.jpa.Persistence.models.program.ProgramCourseList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramCourseListRepository extends JpaRepository<ProgramCourseList, Integer> {
}
