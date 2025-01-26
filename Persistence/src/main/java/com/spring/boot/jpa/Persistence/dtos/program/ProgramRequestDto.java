package com.spring.boot.jpa.Persistence.dtos.program;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.*;

import java.util.List;

public record ProgramRequestDto(
      @NotNull
      @NotBlank
      @NotEmpty
      @Length(min = 2, max = 10)
      String programCode,
      @NotNull
      @NotBlank
      @NotEmpty
      @Length(min = 2, max = 255)
      String programName,
      @NotNull
      @NotBlank
      @NotEmpty
      @Length(min = 2, max = 10)
      String departmentCode,
      List<ProgramCourseListRequestDto> courseList
) {}
