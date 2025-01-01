package com.spring.boot.jpa.Persistence.controllers.student;

import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class StudentController {
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/form")
    public List<Student> getAllStudentsForm(){
        //return studentRepository.findAll();
        var list = new ArrayList<Student>();
        list.add(new Student());
        return list;
    }
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Sdto obj){
        var student = new Student();
        student.setFirstname(obj.getFirstname());
        student.setNrcNumber(obj.getNrc_number());
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }
}


@Data
@NoArgsConstructor
@Component
class Sdto {
    private String nrc_number;
    private String student_id;
    private String firstname;
    private String lastname;
    private String address;
}
