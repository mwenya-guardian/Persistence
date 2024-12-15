package com.spring.boot.jpa.Persistence.Services.student;

import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
}
