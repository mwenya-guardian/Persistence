package com.spring.boot.jpa.Persistence.dtos.school;

import jakarta.persistence.Column;

public record SchoolRequestDto (
        String schoolCode,
        String schoolName
){}