package com.spring.boot.jpa.Persistence.dtos.program;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public record ProgramCourseListRequestDto(
        @NotNull
        @Range(min = 1, max = 9)
        int year,
        @NotNull
        @NotBlank
        @NotEmpty
        @Length(min = 2, max = 10)
        String courseCode
) {
}
