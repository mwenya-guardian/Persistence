package com.spring.boot.jpa.Persistence.dtos.lecture;

import java.sql.Timestamp;
import java.time.LocalTime;

public record LectureResponseDto (
        String code,
        String courseName,
        String courseCode,
        String lecturerName,
        LocalTime startTime,
        LocalTime endTime
) {}
