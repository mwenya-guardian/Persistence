package com.spring.boot.jpa.Persistence.dtos.program;

import java.util.List;

public record ProgramResponseDto(
        String programCode,
        String programName,
        String schoolName,
        String schoolCode,
        String departmentName,
        String departmentCode,
        List<String> programCourseList
) {}
