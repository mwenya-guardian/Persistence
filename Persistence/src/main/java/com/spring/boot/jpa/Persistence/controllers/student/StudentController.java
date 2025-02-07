package com.spring.boot.jpa.Persistence.controllers.student;

import com.spring.boot.jpa.Persistence.Services.student.StudentService;
import com.spring.boot.jpa.Persistence.dtos.student.StudentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return new ResponseEntity<>(studentService.findAllStudents()
        , HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<StudentResponseDto>> getAllStudentsByPaging(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("sort") String sort
    ){
        return new ResponseEntity<>(
                studentService.findAllStudentsUsingPages(pageNumber, pageSize, sort),
                HttpStatus.OK);
    }
    @GetMapping("/{studentNumber}")
    public ResponseEntity<StudentResponseDto> getStudentWithId(
            @PathVariable("studentNumber") String studentNumber
    ){
        var student = studentService.findStudentByStudentNumber(studentNumber);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @GetMapping("/columns")
    public ResponseEntity<List<Object[]>> getAllStudentsWithColumns(
            @RequestParam("columns") String ...columns
    ){
        return new ResponseEntity<>(
                studentService.findAllStudentsWithCustomFieldsSafe(columns),
                HttpStatus.OK);
    }
    @GetMapping("/{studentNumber}/columns")
    public ResponseEntity<Object[]> getStudentsWithColumns(
            @PathVariable("studentNumber") String studentNumber,
            @RequestParam("columns") String[] columns
    ){
        return new ResponseEntity<>(
                studentService.findStudentWithCustomFieldsSafe(studentNumber, columns),
                HttpStatus.OK);
    }
    @GetMapping("/field")
    public ResponseEntity<List<StudentResponseDto>> getStudentsWithFields(
            @RequestParam("name") String[] columnName,
            @RequestParam("value") String[] columnValue
    ) throws Exception{
        return new ResponseEntity<>(
                studentService.findAllStudentsWithFieldValue(columnName, columnValue),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<StudentResponseDto> createNewStudent(
            @RequestBody @Valid StudentRequestDto requestDto
    ){
        return new ResponseEntity<>(
                studentService.createStudent(requestDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{studentNumber}/update")
    public ResponseEntity<StudentResponseDto> updateStudentSafe(
            @RequestBody HashMap<String, String> studentFields,
            @PathVariable("studentNumber") String studentNumber
    ){

        return new ResponseEntity<>(
                studentService.updateStudentCustomSafeAndScalable(studentFields, studentNumber),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentNumber}/delete")
    public ResponseEntity<HashMap<String, String>> deleteStudent(
            @PathVariable("studentNumber") String studentNumber
    ){
        var map = new HashMap<String, String>();
        var affectedRows = studentService.deleteStudentsWithStudentNumber(studentNumber);
        var message = affectedRows==1?
                "Deleted":"Failed";
        map.put("message", message);
        map.put("affectedRows", String.valueOf(affectedRows));
        return new ResponseEntity<>(map, affectedRows == 1?HttpStatus.ACCEPTED:HttpStatus.NOT_MODIFIED);
    }
}