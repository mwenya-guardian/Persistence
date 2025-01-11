package com.spring.boot.jpa.Persistence.controllers.department;

import com.spring.boot.jpa.Persistence.Services.department.DepartmentService;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments(){
        return new ResponseEntity<>(departmentService.findAllDepartment(),
                HttpStatus.OK);
    }
}
