package com.spring.boot.jpa.Persistence.controllers.lecturer;

import com.spring.boot.jpa.Persistence.Services.lecturer.LecturerService;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerRequestDto;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/lecturers")
@AllArgsConstructor
public class LecturerController {
    private LecturerService lecturerService;

    @GetMapping
    public ResponseEntity<List<LecturerResponseDto>> getAllLecturers(){
        return new ResponseEntity<>(
                lecturerService.findAllLecturer(),
                HttpStatus.OK);
    }
//    @GetMapping("/name")
//    public ResponseEntity<List<LecturerResponseDto>> getAllLecturersWithName(
//            @RequestParam String nameType
//    ){
//        if(nameType.equals("lastname")){
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else if (nameType.equals("firstname")){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
    @GetMapping("/page")
    public ResponseEntity<List<LecturerResponseDto>> getAllLecturerOnPages(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sort
    ){
        return new ResponseEntity<>(lecturerService.findAllLecturersUsingPaging(pageNumber, pageSize, sort),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LecturerResponseDto> createNewLecturer(@RequestBody LecturerRequestDto lecturerRequestDto){
        return new ResponseEntity<>(lecturerService.createLecturer(lecturerRequestDto),HttpStatus.CREATED);
    }
    @PutMapping("/{lecturerId}/update")
    public ResponseEntity<LecturerResponseDto> updateLecturer(
            @RequestBody LecturerRequestDto lecturerRequestDto,
            @PathVariable String lecturerId
    ){
        return new ResponseEntity<>(lecturerService.updateLecturer(lecturerRequestDto, lecturerId),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{lecturerId}/delete")
    public ResponseEntity<HashMap<String, String>> deleteLecturerWithCode(@PathVariable String lecturerId){
        var map = new HashMap<String, String>();
        var affectedRows = lecturerService.deleteLecturerWithLecturerId(lecturerId);
        var message = affectedRows==1?
                "Deleted":"Delete Failed";
        map.put("message", message);
        map.put("Affected_Rows", String.valueOf(affectedRows));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
