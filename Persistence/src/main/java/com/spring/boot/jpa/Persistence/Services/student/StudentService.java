package com.spring.boot.jpa.Persistence.Services.student;

import com.spring.boot.jpa.Persistence.dtos.student.StudentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
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
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }
    public Student findStudentByStudentId(String studentId){
        return studentRepository.findByStudentId(studentId);
    }
    public Student findStudentsByNrc(String nrc){
        return studentRepository.findByNrcNumber(nrc);
    }

    //Delete
    public void deleteStudentByNrc(String nrc){
        studentRepository.deleteByNrcNumber(nrc);
    }
}
