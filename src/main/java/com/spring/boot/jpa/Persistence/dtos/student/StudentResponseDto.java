package com.spring.boot.jpa.Persistence.dtos.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record StudentResponseDto (
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
        LocalDate dob,
        LocalDateTime enrollmentDate,
        String schoolName,
        String programName,
        String departmentName,
        String schoolCode,
        String programCode,
        String departmentCode
){
    public static StudentResponseDto withOutJoins(String nrcNumber,
                              String studentId,
                              String firstname,
                              String lastname,
                              String address,
                              String province,
                              String district,
                              String nationality,
                              String phoneNumber,
                              String email,
                              LocalDate dob,
                              LocalDateTime enrollmentDate
                              ){
        return new StudentResponseDto(nrcNumber, studentId, firstname, lastname, address, province,
                district, nationality, phoneNumber, email, dob, enrollmentDate,
                " ", " ", " ", " ", " ", " ");

    }
}
