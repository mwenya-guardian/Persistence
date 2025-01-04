package com.spring.boot.jpa.Persistence.dtos.lecturer;

import java.util.Date;

public record LecturerResponseDto (
        String nrcNumber,
        String lecturerId,
        String firstname,
        String lastname,
        Date dob,
        String address,
        String province,
        String district,
        String nationality,
        String phoneNumber,
        String email,
        String departmentCode,
        String departmentName
) {}