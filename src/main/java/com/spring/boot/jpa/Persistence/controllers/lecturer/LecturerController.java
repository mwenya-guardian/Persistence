package com.spring.boot.jpa.Persistence.controllers.lecturer;

import com.spring.boot.jpa.Persistence.services.lecturer.LecturerService;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerRequestDto;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerResponseDto;
import jakarta.validation.Valid;
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
    @GetMapping("/page")
    public ResponseEntity<List<LecturerResponseDto>> getAllLecturerOnPages(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sort
    ){
        return new ResponseEntity<>(lecturerService.findAllLecturersUsingPaging(pageNumber, pageSize, sort),
                HttpStatus.OK);
    }
    @GetMapping("/{lecturerNumber}")
    public ResponseEntity<LecturerResponseDto> getLecturerWithLecturerNumber(
            @PathVariable String lecturerNumber
    ){
        return new ResponseEntity<>(lecturerService.findLecturerWithLecturerNumber(lecturerNumber),
                HttpStatus.OK);
    }
    @GetMapping("/{nrc}/nrc")
    public ResponseEntity<LecturerResponseDto> getLecturerWithNrc(
            @PathVariable String nrc
    ){
        return new ResponseEntity<>(lecturerService.findLecturerWithNrcNumber(nrc),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LecturerResponseDto> createNewLecturer(
            @RequestBody @Valid LecturerRequestDto lecturerRequestDto){
        return new ResponseEntity<>(lecturerService.createLecturer(lecturerRequestDto),HttpStatus.CREATED);
    }
    @PutMapping("/{lecturerNumber}/update")
    public ResponseEntity<LecturerResponseDto> updateLecturer(
            @RequestBody LecturerRequestDto lecturerRequestDto,
            @PathVariable String lecturerNumber
    ){
        return new ResponseEntity<>(lecturerService.updateLecturer(lecturerRequestDto, lecturerNumber),
                HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{lecturerNumber}/delete")
    public ResponseEntity<HashMap<String, String>> deleteLecturerWithCode(@PathVariable String lecturerNumber){
        var map = new HashMap<String, String>();
        var affectedRows = lecturerService.deleteLecturerWithLecturerId(lecturerNumber);
        var message = affectedRows==1?
                "Deleted":"Delete Failed";
        map.put("message", message);
        map.put("Affected_Rows", String.valueOf(affectedRows));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
