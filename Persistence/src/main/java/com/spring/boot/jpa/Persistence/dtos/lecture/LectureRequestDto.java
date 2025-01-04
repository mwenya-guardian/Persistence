package com.spring.boot.jpa.Persistence.dtos.lecture;


import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;

import java.sql.Timestamp;

public record LectureRequestDto (
        String code,
        String courseId,
        String lecturerId,
        Timestamp startTime,
        Timestamp endTime
) {}
