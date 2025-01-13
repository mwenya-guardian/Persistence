package com.spring.boot.jpa.Persistence.controllers.course;

import com.spring.boot.jpa.Persistence.Services.course.CourseService;
import com.spring.boot.jpa.Persistence.dtos.course.CourseRequestDto;
import com.spring.boot.jpa.Persistence.dtos.course.CourseResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping
    private ResponseEntity<List<CourseResponseDto>> getAllCourses(){
        return new ResponseEntity<>(courseService.findAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/name")
    private ResponseEntity<List<CourseResponseDto>> getAllCourseWithName(@RequestParam String name){
        return new ResponseEntity<>(courseService.findAllCourseByName(name), HttpStatus.OK);
    }
    @GetMapping("/department")
    private ResponseEntity<List<CourseResponseDto>> getAllCoursesWithDepartmentCode(@RequestParam String department){
        return new ResponseEntity<>(courseService.findAllCoursesByDepartment(department), HttpStatus.OK);
    }
    @GetMapping("/{code}")
    public ResponseEntity<CourseResponseDto> getCourseWithCode(@PathVariable String code){
        return new ResponseEntity<>(courseService.findCourseWithCourseCode(code), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> createNewCourse(@RequestBody @Valid CourseRequestDto courseRequestDto){
        return new ResponseEntity<>(courseService.createCourse(courseRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("{code}/update")
    public ResponseEntity<Map> updateCourse(@RequestBody CourseRequestDto courseRequestDto,
                                            @PathVariable String code
    ){
        var updatedMap = courseService.updateCourse(courseRequestDto, code);
        return new ResponseEntity<>(updatedMap, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{code}/delete")
    public ResponseEntity<HashMap<String, String>> deleteCourseWithCode(@PathVariable String code){
        var affectedRows = courseService.deleteCourse(code);
        var message = affectedRows==1?
                "Successfully Deleted":"Error: Delete Failed";
        var map = new HashMap<String, String>();
        map.put("message", message);
        map.put("affected rows", String.valueOf(affectedRows));
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
