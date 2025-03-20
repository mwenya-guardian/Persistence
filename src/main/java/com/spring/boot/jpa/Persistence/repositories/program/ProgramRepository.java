package com.spring.boot.jpa.Persistence.repositories.program;

import com.spring.boot.jpa.Persistence.models.program.Program;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
    Optional<Program> findByProgramCode(String code);
    List<Program> findAllByProgramNameLike(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Program p WHERE p.programCode = :code")
    Integer deleteByProgramCode(String code);
}
