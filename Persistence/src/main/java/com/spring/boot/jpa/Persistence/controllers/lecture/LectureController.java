package com.spring.boot.jpa.Persistence.controllers.lecture;

import com.spring.boot.jpa.Persistence.Services.lecture.LectureService;
import com.spring.boot.jpa.Persistence.Services.lecturer.LecturerService;
import com.spring.boot.jpa.Persistence.dtos.lecture.LectureRequestDto;
import com.spring.boot.jpa.Persistence.dtos.lecture.LectureResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/lectures")
@AllArgsConstructor
public class LectureController {
    private LectureService lectureService;

    @GetMapping
    public ResponseEntity<List<LectureResponseDto>> getAllLectures(){
        return new ResponseEntity<>(lectureService.findAllLectures(),HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<List<LectureResponseDto>> getAllProgramsUsingPages(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam String sort
    ){
        return new ResponseEntity<>(lectureService.findAllLecturesUsingPages(pageNumber, pageSize, sort), HttpStatus.OK);
    }
    @GetMapping("/{code}")
    public ResponseEntity<LectureResponseDto> getLectureWithId(@PathVariable String code){
        return new ResponseEntity<>(lectureService.findLectureWithCode(code), HttpStatus.OK);
    }
    @GetMapping("/courseCode")
    public ResponseEntity<List<LectureResponseDto>> getAllLecturesWithCourse(
            @RequestParam("course_code") String course
    ){
        return new ResponseEntity<>(lectureService.findAllLecturesWithCourse(course),HttpStatus.OK);
    }
    @GetMapping("/lecture")
    public ResponseEntity<List<LectureResponseDto>> getAllLecturesWithLecturer(
            @RequestParam String lecturerId
    ){
        return new ResponseEntity<>(lectureService.findAllLecturesWithLecturer(lecturerId),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LectureResponseDto> createNewLecture(@RequestBody LectureRequestDto lectureRequestDto){
        return new ResponseEntity<>(
                lectureService.createLecture(lectureRequestDto),
                HttpStatus.CREATED);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<LectureResponseDto> updateLecture(
            @RequestBody LectureRequestDto lectureRequestDto,
            @PathVariable Integer id
    ){
        return new ResponseEntity<>(
                lectureService.updateLecture(lectureRequestDto),
                HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{code}/delete")
    public ResponseEntity<HashMap<String, String>> deleteLecture(
            @PathVariable String code
    ){
        var map = new HashMap<String, String>();
        var affectedRows = lectureService.deleteLectureWithLectureCode(code);
        var message = affectedRows==1?
                "Deleted":"Failed";
        map.put("message", message);
        map.put("affectedRows", String.valueOf(affectedRows));
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
