package com.spring.boot.jpa.Persistence.Services.program;

import com.spring.boot.jpa.Persistence.Services.course.CourseService;
import com.spring.boot.jpa.Persistence.Services.department.DepartmentService;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramCourseListRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.program.ProgramCourseList;
import com.spring.boot.jpa.Persistence.repositories.program.ProgramCourseListRepository;
import com.spring.boot.jpa.Persistence.repositories.program.ProgramRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
@AllArgsConstructor
public class ProgramService {
    private ProgramCourseListRepository programCourseListRepository;
    private ProgramRepository programRepository;
    private DepartmentService departmentService;
    private CourseService courseService;
    private ModelMappers modelMappers;

    //Create
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto){
        var newProgram = modelMappers.mapToProgram(programRequestDto);
            var department = departmentService.findByDepartmentCode(programRequestDto.departmentCode());
                newProgram.setDepartment(department);
                newProgram.setSchool(department.getSchool());
        var savedProgram = programRepository.saveAndFlush(newProgram);
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
        var newProgram = modelMappers.mapToProgram(programRequestDto);
            programRepository.findById(programId).orElseThrow();
            var department = departmentService.findByDepartmentCode(programRequestDto.departmentCode());
                newProgram.setProgramId(programId);
                newProgram.setDepartment(department);
                newProgram.setSchool(department.getSchool());
        var savedProgram = programRepository.save(newProgram);
        return modelMappers.mapToProgramResponse(savedProgram);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public ProgramResponseDto updateProgramCourseList(List<ProgramCourseListRequestDto> courseList, Integer id){
        var program = programRepository.findById(id)
                .orElseThrow();
        List<ProgramCourseList> programCourseList = program.getProgramCourseList()
                .orElse(new ArrayList<>());
        courseList.forEach(args ->{
            if(programCourseList.stream().noneMatch(existing -> existing.getCourse().getCourseCode()
                                        .equals(args.courseCode()))){
                ProgramCourseList newProgramCourseList = new ProgramCourseList();
                    newProgramCourseList.setCourse(
                            courseService.findByCourseCode(args.courseCode())
                    );
                    newProgramCourseList.setProgram(program);
                    newProgramCourseList.setYear(args.year()+"");
                    programCourseListRepository.save(newProgramCourseList);
                    programCourseList.add(programCourseListRepository.save(newProgramCourseList));
            }
        });
        var updatedProgram = programRepository.saveAndFlush(program);
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
