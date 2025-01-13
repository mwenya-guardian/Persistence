package com.spring.boot.jpa.Persistence.Services.student;

import com.spring.boot.jpa.Persistence.dtos.student.StudentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
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
            Integer Id = studentRepository.findByStudentNumberQuery(studentNumber)
                    .orElseThrow()
                    .getId();
            newStudent.setId(Id);
        var savedStudent = studentRepository.save(newStudent);
        return modelMappers.mapToStudentResponse(savedStudent);
    }

    //Retrieve
    public List<StudentResponseDto> findAllStudents(){
        return studentRepository.findAllQuery()
                .stream()
                .parallel()
                .map(modelMappers::mapToStudentResponseWithOutJoin)
                .toList();
    }
    public StudentResponseDto findStudentByStudentId(String studentId){
        var student = studentRepository.findByStudentNumber(studentId)
                .orElse(new Student());
        return modelMappers.mapToStudentResponse(student);
    }
    public StudentResponseDto findStudentsByNrc(String nrc){
        var student = studentRepository.findByNrcNumber(nrc)
                .orElse(new Student());
        return modelMappers.mapToStudentResponse(student);
    }
    public List<StudentResponseDto> findAllStudentsUsingPages(Integer pageNumber, Integer pageSize, String sort){
        Pageable pageable;
        if(!sort.isEmpty())
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort).ascending());
        else
            pageable = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageable)
                .stream()
                .parallel()
                .map(modelMappers::mapToStudentResponse)
                .toList();
    }

    //Delete
    public int deleteStudentsWithStudentNumber(String number){
        return studentRepository.deleteByStudentNumber(number);
    }
    public int deleteStudentByNrc(String nrc){
        return studentRepository.deleteByNrcNumber(nrc);
    }
    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }
}
