package com.spring.boot.jpa.Persistence.dtos.course;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CourseRequestDto(
    @NotEmpty
    String courseCode,
    @NotEmpty
    String courseName,
    @NotEmpty
    @Positive
    Integer departmentId
) {}
