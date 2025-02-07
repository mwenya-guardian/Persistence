package com.spring.boot.jpa.Persistence.dtos.lecturer;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

public record LecturerRequestDto (
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
         @Length(min = 1, max = 255)
         String province,
         @NotNull
         @NotEmpty
         @Length(min = 1, max = 255)
         String district,
         @NotNull
         @NotEmpty
         @Length(min = 1, max = 255)
         String nationality,
         @NotNull
         @NotEmpty
         @Length(min = 10, max = 20)
         String phoneNumber,
         @NotNull
         @NotEmpty
         @Email
         String email,
         @NotNull
         @Past
         LocalDate dob,
         @NotNull
         @Positive
         Integer departmentId
) {}
