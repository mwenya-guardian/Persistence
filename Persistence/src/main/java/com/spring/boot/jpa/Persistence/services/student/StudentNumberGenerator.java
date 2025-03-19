package com.spring.boot.jpa.Persistence.services.student;

import com.spring.boot.jpa.Persistence.models.student.StudentNumberTable;
import com.spring.boot.jpa.Persistence.repositories.student.StudentNumberTableRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
@AllArgsConstructor
public class StudentNumberGenerator {
    private StudentNumberTableRepository studentNumberTableRepository;

    @Transactional
    public String newStudentNUmberGenerate(){
        String year = Year.now().toString();
        String studentNumber;
        var studentNumberTable = studentNumberTableRepository.findById(0)
                .orElse(new StudentNumberTable());
            if(studentNumberTable.getYear() == null){
                studentNumberTable.setId(0);
                studentNumberTable.setYear(year);
                studentNumberTable.setNumber((long)1);
                studentNumberTable = studentNumberTableRepository.saveAndFlush(studentNumberTable);
            }
        if(studentNumberTable.getYear().equals(year)){
            String number = String.valueOf(
                    studentNumberTable.getNumber() + 1
            );
            String paddedNumber = String.format("%6s", number).replace(' ', '0');
            studentNumber = year + paddedNumber;
            studentNumberTable.setNumber(
                    studentNumberTable.getNumber() + 1
            );
            studentNumberTableRepository.save(studentNumberTable);
        }
        else {
            studentNumberTable.setYear(year);
            studentNumberTable.setNumber((long)2);
            studentNumberTableRepository.save(studentNumberTable);
            String paddedNumber = String.format("%6s", "1").replace(' ', '0');
            studentNumber = year + paddedNumber;
        }
        return studentNumber;
    }
}
