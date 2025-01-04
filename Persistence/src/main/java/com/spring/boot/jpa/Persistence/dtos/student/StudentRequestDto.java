package com.spring.boot.jpa.Persistence.dtos.student;

import java.util.Date;

public record StudentRequestDto (
        String nrcNumber,
        String studentId,
        String firstname,
        String lastname,
        String address,
        String province,
        String district,
        String nationality,
        String phoneNumber,
        String email,
        Date dob,
        Date enrollmentDate,
        String schoolId,
        String programId,
        String departmentId
){}
