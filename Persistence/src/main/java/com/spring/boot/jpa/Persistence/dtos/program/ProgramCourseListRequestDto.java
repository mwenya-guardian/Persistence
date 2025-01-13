package com.spring.boot.jpa.Persistence.dtos.program;

public record ProgramCourseListRequestDto(
        String year,
        Integer course
) {
}
