package com.spring.boot.jpa.Persistence.dtos.lecturer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record LecturerResponseDto (
        String nrcNumber,
        String lecturerNumber,
        String firstname,
        String lastname,
        LocalDate dob,
        LocalDateTime enrollment,
        String address,
        String province,
        String district,
        String nationality,
        String phoneNumber,
        String email,
        String departmentCode,
        String departmentName
) {}