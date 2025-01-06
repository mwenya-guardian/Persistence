package com.spring.boot.jpa.Persistence.repositories.program;

import com.spring.boot.jpa.Persistence.models.program.Program;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
    Optional<Program> findByProgramCode(String code);
    List<Program> findAllByProgramNameLike(String name);
    @Transactional
    void deleteByProgramCode(String code);
}
