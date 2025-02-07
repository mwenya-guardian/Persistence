package com.spring.boot.jpa.Persistence.controllers.department;

import com.spring.boot.jpa.Persistence.Services.department.DepartmentService;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import jakarta.validation.Valid;
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
    @GetMapping("/page")
    public ResponseEntity<List<DepartmentResponseDto>> getAllProgramsUsingPages(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam String sort
    ){
        return new ResponseEntity<>(departmentService.findAllDepartmentsUsingPages(pageNumber, pageSize, sort), HttpStatus.OK);
    }
    @GetMapping("/name")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartmentsWithName(
            @RequestParam String name
    ){
        return new ResponseEntity<>(departmentService.findAllDepartmentsByName(name),HttpStatus.OK);
    }
    @GetMapping("/code")
    public ResponseEntity<DepartmentResponseDto> getDepartmentWithCode(
            @RequestParam("department_code") String code
    ){
        return new ResponseEntity<>(departmentService.findWithDepartmentCode(code),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createNewDepartment(
            @RequestBody @Valid DepartmentRequestDto departmentRequestDto
    ){
        return new ResponseEntity<>(departmentService.createDepartment(departmentRequestDto), HttpStatus.CREATED);
    }
    @PutMapping("/{code}/update")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @RequestBody DepartmentRequestDto departmentRequestDto,
            @PathVariable String code
    ){
        return new ResponseEntity<>(departmentService.updateDepartment(departmentRequestDto, code),
                HttpStatus.ACCEPTED);
    }
    @PatchMapping("/{code}/hod/update")
    public ResponseEntity<DepartmentResponseDto> updateDepartmentHod(
            @PathVariable String code,
            @RequestParam("lecture_number") String lecturerNumber
    ){
        return new ResponseEntity<>(departmentService.updateDepartmentHod(code, lecturerNumber),
                HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{code}/delete")
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
