package com.spring.boot.jpa.Persistence.repositories.lecture;

import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    List<Lecture> findAllByStartTimeBetween(Timestamp start, Timestamp end);
    @Query(value = "SELECT l FROM Lecture l WHERE l.lectureCode = :code")
    Optional<Lecture> findByLectureCode(String code);
    @Query(value = "SELECT l FROM Lecture l WHERE l.course.courseId = :courseId")
    List<Lecture> findAllByCourseId(Integer courseId);
    @Query(value = "SELECT l FROM Lecture l WHERE l.lecturer.id = :lecturerId")
    List<Lecture> findAllByLecturerId(Integer lecturerId);

    @Query(value = "DELETE FROM Lecture l WHERE l.lectureCode = :code")
    Integer deleteByLectureCode(String code);
}
