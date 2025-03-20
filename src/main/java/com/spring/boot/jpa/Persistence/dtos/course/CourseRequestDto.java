package com.spring.boot.jpa.Persistence.dtos.course;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record CourseRequestDto(
    @NotEmpty
    @NotNull
    @Length(min = 2, max = 10)
    String courseCode,
    @NotNull
    @NotEmpty
    @Length(min = 2, max = 255)
    String courseName,
    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 2, max = 10)
    String departmentCode
) {}
