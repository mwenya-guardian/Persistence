package com.spring.boot.jpa.Persistence.dtos.program;

import com.spring.boot.jpa.Persistence.dtos.course.CourseResponseDto;

import java.util.List;

public record ProgramResponseDto(
        Integer programId,
        String programCode,
        String programName,
        String schoolName,
        String schoolCode,
        String departmentName,
        String departmentCode,
        List<CourseResponseDto> programCourseList
) {}
