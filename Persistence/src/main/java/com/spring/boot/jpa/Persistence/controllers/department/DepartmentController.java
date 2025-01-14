package com.spring.boot.jpa.Persistence.controllers.department;

import com.spring.boot.jpa.Persistence.Services.department.DepartmentService;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @GetMapping("/name")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartmentsWithName(
            @RequestParam String name
    ){
        return new ResponseEntity<>(departmentService.findAllDepartmentsByName(name),HttpStatus.OK);
    }
    @GetMapping("/code")
    public ResponseEntity<DepartmentResponseDto> getDepartmentWithCode(
            @RequestParam(" department_code") String code
    ){
        return new ResponseEntity<>(departmentService.findByDepartmentCode(code),HttpStatus.OK);
    }
    @PutMapping("/{code}/update")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @RequestBody DepartmentRequestDto departmentRequestDto,
            @PathVariable String code
    ){
        return new ResponseEntity<>(departmentService.updateDepartment(departmentRequestDto, code),
                HttpStatus.ACCEPTED);
    }
    @DeleteMapping
    public ResponseEntity<HashMap<String, String>> deleteDepartment(@PathVariable String code){
        var map = new HashMap<String, String>();
        var affectedRows = departmentService.deleteDepartment(code);
        var message = affectedRows==1?
                "Deleted":"Failed";
        map.put("message", message);
        map.put("affectedRows", String.valueOf(affectedRows));
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
