package com.spring.boot.jpa.Persistence.dtos.lecturer;

import java.util.Date;

public record LecturerRequestDto (    
         String nrcNumber,
         String lecturerId,
         String firstname,
         String lastname,
         String address,
         String province,
         String district,
         String nationality,
         String phoneNumber,
         String email,
         Date dob,
         String departmentId
) {}
