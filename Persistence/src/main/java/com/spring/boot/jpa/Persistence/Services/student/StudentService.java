package com.spring.boot.jpa.Persistence.Services.student;

import com.spring.boot.jpa.Persistence.dtos.student.StudentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private ModelMappers modelMappers;

    //Create
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto){
        var newStudent = modelMappers.mapToStudent(studentRequestDto);
        var savedStudent = studentRepository.save(newStudent);
        return modelMappers.mapToStudentResponse(savedStudent);
    }

    //Update
    public StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, Integer Id){
        var newStudent = modelMappers.mapToStudent(studentRequestDto);
            newStudent.setId(Id);
        var savedStudent = studentRepository.save(newStudent);
        return modelMappers.mapToStudentResponse(savedStudent);
    }
    public StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, String studentNumber){
        var newStudent = modelMappers.mapToStudent(studentRequestDto);
            Integer Id = studentRepository.findByStudentId(studentNumber)
                    .getId();
        var savedStudent = studentRepository.save(newStudent);
        return modelMappers.mapToStudentResponse(savedStudent);
    }

    //Retrieve
    public List<StudentResponseDto> findAllStudents(){
        return studentRepository.findAll()
                .stream()
                .parallel()
                .map(modelMappers::mapToStudentResponse)
                .toList();
    }
    public StudentResponseDto findStudentByStudentId(String studentId){
        var student = studentRepository.findByStudentId(studentId);
        return modelMappers.mapToStudentResponse(student);
    }
    public StudentResponseDto findStudentsByNrc(String nrc){
        var student = studentRepository.findByNrcNumber(nrc);
        return modelMappers.mapToStudentResponse(student);
    }

    //Delete
    public void deleteStudentByNrc(String nrc){
        studentRepository.deleteByNrcNumber(nrc);
    }
}
