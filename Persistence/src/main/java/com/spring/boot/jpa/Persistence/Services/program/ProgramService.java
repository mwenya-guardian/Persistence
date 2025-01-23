package com.spring.boot.jpa.Persistence.Services.program;

import com.spring.boot.jpa.Persistence.Services.course.CourseService;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramCourseListRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramCourseListResponseDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.program.ProgramCourseList;
import com.spring.boot.jpa.Persistence.repositories.program.ProgramRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@AllArgsConstructor
public class ProgramService {
    private ProgramRepository programRepository;
    private CourseService courseService;
    private ModelMappers modelMappers;

    //Create
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto){
        var newProgram = modelMappers.mapToProgram(programRequestDto, null);
        var savedProgram = programRepository.save(newProgram);
            if(programRequestDto.courseList() != null && !programRequestDto.courseList().isEmpty()){
                this.updateProgramCourseList(programRequestDto.courseList(), savedProgram.getProgramId());
            }
        return modelMappers.mapToProgramResponse(
                programRepository.findById(
                        savedProgram.getProgramId())
                        .orElseThrow()
        );
    }

    //Update
    public ProgramResponseDto updateProgram(ProgramRequestDto programRequestDto, Integer programId){
        var newProgram = modelMappers.mapToNewProgram(programRequestDto);
        programRepository.findById(programId).orElseThrow();
            newProgram.setProgramId(programId);
        var savedProgram = programRepository.save(newProgram);
        return modelMappers.mapToProgramResponse(savedProgram);
    }

    @Transactional
    public ProgramResponseDto updateProgramCourseList(List<ProgramCourseListRequestDto> courseList, Integer id){
        var program = programRepository.findById(id)
                .orElseThrow();
        var programCourseList = program.getProgramCourseList();
        courseList.forEach(args ->{
            if(programCourseList.stream().noneMatch(existing -> existing.getCourse().getCourseId()
                                        .equals(args.course()))){
                programCourseList.add(
                        new ProgramCourseList(
                                args.year(),
                                new Program(program.getProgramId()),
                                new Course(args.course()))
                );
            }
        });
        var updatedProgram = programRepository.save(program);
        return modelMappers.mapToProgramResponse(updatedProgram);

    }
    //Retrieve
    public List<ProgramResponseDto> findAllProgramsWithName(String name){
        return programRepository.findAllByProgramNameLike(name)
                .stream()
                .parallel()
                .map(modelMappers::mapToProgramResponse)
                .toList();
    }
    public List<ProgramResponseDto> findAllPrograms(){
        return programRepository.findAll()
                .stream()
                .parallel()
                .map(modelMappers::mapToProgramResponse)
                .toList();
    }
    public ProgramResponseDto findProgramWithCode(String code){
        var program = programRepository.findByProgramCode(code);
        return modelMappers.mapToProgramResponse(program.orElse(new Program()));
    }
    public Program findByProgramId(Integer id){
        return programRepository.findById(id).orElseThrow();
    }

    //Delete
    public Integer deleteProgramWithCode(String code){
        return programRepository.deleteByProgramCode(code);
    }
    public void deleteProgramWithId(Integer id){
        programRepository.deleteById(id);
    }
}
