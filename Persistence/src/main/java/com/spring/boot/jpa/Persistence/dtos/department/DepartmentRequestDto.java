package com.spring.boot.jpa.Persistence.dtos.department;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record DepartmentRequestDto(
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    String departmentName,
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    String departmentCode,
    @Length(min = 10, max = 12)
    //@Digits(integer = 12, fraction = 0)
    String lecturerNumber,
    @NotNull
    @Positive
    Integer schoolId
) {}
