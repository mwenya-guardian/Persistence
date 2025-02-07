package com.spring.boot.jpa.Persistence.dtos.student;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record StudentRequestDto (
        @NotNull
        @NotEmpty
        @Length(min = 11, max = 12)
        String nrcNumber,
        @NotNull
        @NotEmpty
        @Length(min = 1, max = 255)
        String firstname,
        @NotNull
        @NotEmpty
        @Length(min = 1, max = 255)
        String lastname,
        @NotNull
        @NotEmpty
        @Length(min = 1, max = 255)
        String address,
        @NotNull
        @NotEmpty
        String province,
        @Length(min = 1, max = 255)
        @NotNull
        @NotEmpty
        String district,
        @Length(min = 1, max = 255)
        @NotNull
        @NotEmpty
        String nationality,
        @Length(min = 1, max = 255)
        @NotNull
        @NotEmpty
        String phoneNumber,
        @NotNull
        @NotEmpty
        @Email
        String email,
        @NotNull
        @Past
        LocalDate dob,
//        @FutureOrPresent
//        LocalDateTime enrollmentDate,
        @NotNull
        @NotBlank
        @Length(min = 2, max = 10)
        String schoolCode,
        @NotNull
        @Positive
        Integer programId,
        @NotNull
        @Positive
        Integer departmentId
){}
