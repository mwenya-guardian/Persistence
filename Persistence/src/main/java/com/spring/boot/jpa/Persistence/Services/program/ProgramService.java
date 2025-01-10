package com.spring.boot.jpa.Persistence.Services.program;

import com.spring.boot.jpa.Persistence.dtos.program.ProgramRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.repositories.program.ProgramRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@AllArgsConstructor
public class ProgramService {
    private ProgramRepository programRepository;
    private ModelMappers modelMappers;

    //Create
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto){
        var newProgram = modelMappers.mapToProgram(programRequestDto);
        var savedProgram = programRepository.save(newProgram);
        return modelMappers.mapToProgramResponse(savedProgram);
    }

    //Update
    public ProgramResponseDto updateProgram(ProgramRequestDto programRequestDto){
        var newProgram = modelMappers.mapToProgram(programRequestDto);
            var programId =  programRepository.findByProgramCode(programRequestDto.programCode())
                    .orElseThrow();
            newProgram.setProgramId(programId.getProgramId());
        var savedProgram = programRepository.save(newProgram);
        return modelMappers.mapToProgramResponse(savedProgram);
    }
    public ProgramResponseDto updateProgram(ProgramRequestDto programRequestDto, Integer id){
        var newProgram = modelMappers.mapToProgram(programRequestDto);
            programRepository.findById(id).orElseThrow();
            newProgram.setProgramId(id);
        var savedProgram = programRepository.save(newProgram);
        return modelMappers.mapToProgramResponse(savedProgram);
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

    //Delete
    public Integer deleteProgramWithCode(String code){
        return programRepository.deleteByProgramCode(code);
    }
    public void deleteProgramWithId(Integer id){
        programRepository.deleteById(id);
    }
}
