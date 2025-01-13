package com.spring.boot.jpa.Persistence.dtos.program;

public record ProgramCourseListResponseDto(
        String year,
        String program,
        String course
) {
}
