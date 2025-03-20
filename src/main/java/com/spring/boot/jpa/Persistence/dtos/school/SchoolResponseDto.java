package com.spring.boot.jpa.Persistence.dtos.school;

import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramResponseDto;

import java.util.ArrayList;
import java.util.List;

public record SchoolResponseDto(
        String schoolCode,
        String schoolName,
        List<DepartmentResponseDto> departments,
        List<ProgramResponseDto> programs
){
    public static SchoolResponseDto dtoWithNameAndCodeOnly(String schoolCode, String schoolName){
        return new SchoolResponseDto(schoolCode, schoolName, new ArrayList<>(), new ArrayList<>());
    }
}
