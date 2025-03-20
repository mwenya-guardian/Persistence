package com.spring.boot.jpa.Persistence.controllers.student;

import com.spring.boot.jpa.Persistence.models.student.DistanceStudent;
import com.spring.boot.jpa.Persistence.repositories.student.DistanceStudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class DistanceStudentController {
    private DistanceStudentRepository distanceStudentRepository;

    @RequestMapping("/dstudents")
    public String getAllDistanceStudents(){
        return "sdklfo;hxgcvls;dgzidxhglxuhvlx";
    }
    @RequestMapping("/dstudents/form")
    public List<DistanceStudent> getAllDistanceStudentsForm(){
        var list = new ArrayList<DistanceStudent>();
        list.add(new DistanceStudent());
        return list;
    }

    @PostMapping("/dstudents")
    public ResponseEntity<DistanceStudent> createStudent(@RequestBody Dsdto obj) {
        var student =  new DistanceStudent();
            student.setFirstname(obj.getFirstname());
            student.setStudentNumber(obj.getStudent_id());
            student.setNrcNumber(obj.getNrc_number());
            student.setAddress(obj.getAddress());
            student.setTimeOfStudy(obj.getTimeOfStudy());
        return new ResponseEntity<>(distanceStudentRepository.save(student), HttpStatus.CREATED);
    }

}

@Data
@NoArgsConstructor
@Component
class Dsdto {
    private String nrc_number;
    private String student_id;
    private String firstname;
    private String lastname;
    private String address;
    private Date timeOfStudy;
}