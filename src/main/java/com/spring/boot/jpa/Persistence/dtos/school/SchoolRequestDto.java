package com.spring.boot.jpa.Persistence.dtos.school;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record SchoolRequestDto (
        @NotNull
        @NotBlank
        @NotEmpty
        @Length(min = 2, max = 100)
        String schoolCode,
        @NotNull
        @NotBlank
        @NotEmpty
        @Length(min = 3, max = 100)
        String schoolName
){}