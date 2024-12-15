package com.spring.boot.jpa.Persistence.repositories.lecture;

import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
}
