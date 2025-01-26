package com.spring.boot.jpa.Persistence.dtos.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record DepartmentRequestDto(
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 1, max = 255)
    String departmentName,
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 1, max = 255)
    String departmentCode,
    String lecturerNumber,
    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 2, max = 10)
    String schoolCode
) {}
