package com.spring.boot.jpa.Persistence.mappers.department;

import com.spring.boot.jpa.Persistence.dtos.department.DepartmentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import com.spring.boot.jpa.Persistence.models.department.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department mapToDepartment(DepartmentRequestDto departmentRequestDto){
        Department newDepartment = new Department();
        return newDepartment;
    }
    public DepartmentResponseDto mapToDepartmentResponse(Department department){
        DepartmentResponseDto newDepartmentResponseDto = new DepartmentResponseDto();
        return newDepartmentResponseDto;
    }
}
