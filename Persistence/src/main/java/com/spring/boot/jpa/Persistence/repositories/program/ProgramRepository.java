package com.spring.boot.jpa.Persistence.repositories.program;

import com.spring.boot.jpa.Persistence.models.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
