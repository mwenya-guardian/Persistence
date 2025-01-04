package com.spring.boot.jpa.Persistence.dtos.course;

public record CourseRequestDto(
    String courseCode,
    String courseName,
    String departmentName,
    String departmentCode,
    String departmentId
) {}
