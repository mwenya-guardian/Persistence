package com.spring.boot.jpa.Persistence.controllers.school;

import com.spring.boot.jpa.Persistence.Services.school.SchoolService;
import com.spring.boot.jpa.Persistence.dtos.school.SchoolRequestDto;
import com.spring.boot.jpa.Persistence.dtos.school.SchoolResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/schools")
@AllArgsConstructor
public class SchoolController {
    private SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<SchoolResponseDto>> getAllSchools(){
        return new ResponseEntity<>(schoolService.findAllSchools(), HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<List<SchoolResponseDto>> getAllSchoolsOnPages(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sort){
        return new ResponseEntity<>(
                schoolService.findAllSchoolsUsingPages(pageNumber, pageSize, sort),
                HttpStatus.OK
        );
    }
    @GetMapping("/name")
    public ResponseEntity<List<SchoolResponseDto>> getAllSchoolSWithName(@RequestParam String name){
        return new ResponseEntity<>(
                schoolService.findAllSchoolsWithName(name),
                HttpStatus.OK
        );
    }
    @GetMapping("/{code}")
    public ResponseEntity<SchoolResponseDto> getSchoolWithCode(@PathVariable String code){
        return new ResponseEntity<>(schoolService.findSchoolWithCode(code), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SchoolResponseDto> createNewSchool(
            @RequestBody SchoolRequestDto schoolRequestDto){
        return new ResponseEntity<>(schoolService.createSchool(schoolRequestDto), HttpStatus.OK);
    }
    @PutMapping("{code}/update")
    public ResponseEntity<SchoolResponseDto> updateSchool(@RequestBody SchoolRequestDto schoolRequestDto){
        return new ResponseEntity<>(schoolService.updateSchool(schoolRequestDto)
                ,HttpStatus.OK);
    }
    @DeleteMapping("/{code}/delete")
    public ResponseEntity<HashMap<String, String>> deleteSchoolWithCode(@PathVariable String code){
        var map = new HashMap<String, String>();
        var affectedRows = schoolService.deleteSchoolWithCode(code);
        var message = affectedRows==1?
                "Delete Successful" : "Delete Failed";
            map.put("message", message);
            map.put("affected rows", String.valueOf(affectedRows));
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
