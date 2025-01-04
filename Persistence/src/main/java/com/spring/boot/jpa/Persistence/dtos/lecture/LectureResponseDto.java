package com.spring.boot.jpa.Persistence.dtos.lecture;

import java.sql.Timestamp;

public record LectureResponseDto (
        String code,
        String courseName,
        String courseCode,
        String lecturerName,
        Timestamp startTime,
        Timestamp endTime
) {}
