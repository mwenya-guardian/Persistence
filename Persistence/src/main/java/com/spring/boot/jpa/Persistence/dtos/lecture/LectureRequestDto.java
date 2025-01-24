package com.spring.boot.jpa.Persistence.dtos.lecture;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record LectureRequestDto (
        @NotNull
        @NotEmpty
        @Length(min = 1, max = 255)
        String code,
        @Positive
        Integer courseId,
        @NotNull
        @Positive
        Integer lecturerId,
        @NotNull
        LocalTime startTime,
        @NotNull
        LocalTime endTime
) {}
