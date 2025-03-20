package com.spring.boot.jpa.Persistence.dtos.lecture;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalTime;

public record LectureRequestDto (
        @NotNull
        @NotBlank
        @NotEmpty
        @Length(min = 1, max = 100)
        String code,
        @NotNull
        @NotBlank
        @NotEmpty
        @Length(min = 2, max = 12)
        String courseCode,
        @NotNull
        @NotEmpty
        @NotBlank
        @Length(min = 10, max = 12)
        String lecturerNumber,
        @NotNull
        @NotBlank
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime startTime,
        @NotNull
        @NotBlank
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime endTime
) {}
