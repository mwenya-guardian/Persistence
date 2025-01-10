package com.spring.boot.jpa.Persistence.dtos.student;

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
        Date dob,
        Date enrollmentDate,
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
                              Date dob,
                              Date enrollmentDate
                              ){
        return new StudentResponseDto(nrcNumber, studentId, firstname, lastname, address, province,
                district, nationality, phoneNumber, email, dob, enrollmentDate,
                " ", " ", " ", " ", " ", " ");

    }
}
