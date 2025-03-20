package com.spring.boot.jpa.Persistence.dtos.department;

public record DepartmentResponseDto(
        String departmentName,
        String departmentCode,
        String lecturerName,
        String schoolName,
        String schoolCode
) {}
