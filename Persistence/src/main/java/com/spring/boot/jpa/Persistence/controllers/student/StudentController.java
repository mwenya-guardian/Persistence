package com.spring.boot.jpa.Persistence.controllers.student;

import com.spring.boot.jpa.Persistence.Services.student.StudentService;
import com.spring.boot.jpa.Persistence.dtos.student.StudentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentResponseDto;
import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sort
    ){
        sort = sort.substring(1, sort.lastIndexOf('"'));
        return new ResponseEntity<>(
                studentService.findAllStudentsUsingPages(pageNumber, pageSize, sort),
                HttpStatus.OK);
    }
    @GetMapping("/columns")
    public ResponseEntity<List<Object[]>> getAllStudentsWithColumns(
            @RequestParam String ...columns
    ){
        return new ResponseEntity<>(
                studentService.findAllStudentsWithCustomFieldsSafe(columns),
                HttpStatus.OK);
    }
    @GetMapping("/{studentId}/columns")
    public ResponseEntity<List<Object[]>> getStudentsWithColumns(
            @PathVariable String studentId,
            @RequestParam String ...columns
    ){
        return new ResponseEntity<>(
                studentService.findStudentWithCustomFieldsSafe(studentId, columns),
                HttpStatus.OK);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> getStudentWithId(
            @PathVariable String studentId
    ){
        var student = studentService.findStudentByStudentId(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<StudentResponseDto> createNewStudent(
            @RequestBody @Valid StudentRequestDto requestDto
    ){
        return new ResponseEntity<>(
                studentService.createStudent(requestDto),
                HttpStatus.CREATED);
    }
    @PutMapping("/{studentId}/update")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @RequestBody StudentRequestDto studentRequestDto,
            @PathVariable String studentId
    ){

        return new ResponseEntity<>(
                studentService.updateStudent(studentRequestDto, studentId),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}/delete")
    public ResponseEntity<String> deleteStudent(
            @PathVariable String studentId
    ){
        var affectedRows = studentService.deleteStudentsWithStudentNumber(studentId);
            var body = affectedRows == 1?
                    "{\"message\": \"Deleted Safely\"}":
                    "{\"Error\": \"Delete Failed\"}";
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }
}