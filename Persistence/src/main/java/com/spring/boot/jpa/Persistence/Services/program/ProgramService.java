package com.spring.boot.jpa.Persistence.Services.program;

import com.spring.boot.jpa.Persistence.dtos.program.ProgramRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.repositories.program.ProgramCourseListRepository;
import com.spring.boot.jpa.Persistence.repositories.program.ProgramRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProgramService {
    private ProgramRepository programRepository;
    private ModelMappers modelMappers;
    private ProgramCourseListRepository programCourseListRepository;

    //Create
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto){
        var newProgram = modelMappers.mapToProgram(programRequestDto);
        var savedProgram = programRepository.save(newProgram);
        return modelMappers.mapToProgramResponse(savedProgram);
    }
}
