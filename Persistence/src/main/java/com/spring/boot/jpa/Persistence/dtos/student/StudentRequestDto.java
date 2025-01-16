package com.spring.boot.jpa.Persistence.dtos.student;

import jakarta.validation.constraints.*;

import java.util.Date;

public record StudentRequestDto (
        @NotNull
        @NotEmpty
        String nrcNumber,
        @NotNull
        @NotEmpty
        String firstname,
        @NotNull
        @NotEmpty
        String lastname,
        @NotNull
        @NotEmpty
        String address,
        @NotNull
        @NotEmpty
        String province,
        @NotNull
        @NotEmpty
        String district,
        @NotNull
        @NotEmpty
        String nationality,
        @NotNull
        @NotEmpty
        String phoneNumber,
        @NotNull
        @NotEmpty
        @Email
        String email,
        @NotNull
        @Past
        Date dob,
        @FutureOrPresent
        Date enrollmentDate,
        String schoolId,
        String programId,
        String departmentId
){}
