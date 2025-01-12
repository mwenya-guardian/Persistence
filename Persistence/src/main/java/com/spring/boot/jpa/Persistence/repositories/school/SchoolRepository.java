package com.spring.boot.jpa.Persistence.repositories.school;

import com.spring.boot.jpa.Persistence.models.school.School;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    Optional<School> findBySchoolCode(String code);
    List<School> findAllBySchoolNameLike(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM School s WHERE s.schoolCode = :schoolCode")
    Integer deleteBySchoolCode(String schoolCode);
}
