package com.spring.boot.jpa.Persistence.dtos.program;

import java.util.List;

public record ProgramRequestDto(
      String programCode,
      String programName,
      String schoolId,
      String departmentId,
      List<ProgramCourseListRequestDto> courseList
) {}
