package com.spring.boot.jpa.Persistence.controllers.program;

import com.spring.boot.jpa.Persistence.Services.program.ProgramService;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/programs")
@AllArgsConstructor
public class ProgramController {
    private ProgramService programService;

    @GetMapping
    public ResponseEntity<List<ProgramResponseDto>> getAllPrograms(){
        return new ResponseEntity<>(programService.findAllPrograms(), HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<List<ProgramResponseDto>> getAllProgramsUsingPages(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam String sort
    ){
        return new ResponseEntity<>(programService.findAllProgramsUsingPages(pageNumber, pageSize, sort), HttpStatus.OK);
    }
    @GetMapping("/name")
    public ResponseEntity<List<ProgramResponseDto>> getAllProgramsWithName(@RequestParam String name){
        return new ResponseEntity<>(programService.findAllProgramsWithName(name), HttpStatus.OK);
    }
    @GetMapping("/{code}")
    public ResponseEntity<ProgramResponseDto> getAllProgramWithCode(@PathVariable String code){
        return new ResponseEntity<>(programService.findProgramWithCode(code), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProgramResponseDto> createNewProgram(
            @RequestBody ProgramRequestDto programRequestDto){
        return new ResponseEntity<>(programService.createProgram(programRequestDto),HttpStatus.CREATED);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<ProgramResponseDto> updateProgram(@RequestBody ProgramRequestDto programRequestDto,
    @PathVariable Integer id){
        return new ResponseEntity<>(programService.updateProgram(programRequestDto, id), HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}/update/courses")
    public ResponseEntity<ProgramResponseDto> updateProgramCourseList(
            @RequestBody ProgramRequestDto programRequestDto,
            @PathVariable Integer id
    ){
        return new ResponseEntity<>(
                programService.updateProgramCourseList(programRequestDto.courseList(), id),
                HttpStatus.ACCEPTED
        );
    }
    @DeleteMapping("/{code}/delete")
    public ResponseEntity<HashMap<String, String>> deleteProgramWithCode(@PathVariable String code){
        var map = new HashMap<String, String>();
        var affectedRows = programService.deleteProgramWithCode(code);
        var message = affectedRows==1?
                "Deleted":"Delete Failed";
        map.put("message", message);
        map.put("Affected_Rows", String.valueOf(affectedRows));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
