package com.spring.boot.jpa.Persistence.dtos.department;

public record DepartmentRequestDto(
    String departmentName,
    String departmentCode,
    String lecturerId,
    String schoolId
) {}
