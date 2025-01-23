package com.spring.boot.jpa.Persistence.Services.school;

import com.spring.boot.jpa.Persistence.dtos.school.SchoolRequestDto;
import com.spring.boot.jpa.Persistence.dtos.school.SchoolResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.repositories.school.SchoolRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@AllArgsConstructor
public class SchoolService {
    private SchoolRepository schoolRepository;
    private ModelMappers modelMappers;

    //Create
    public SchoolResponseDto createSchool(SchoolRequestDto schoolRequestDto){
        var newSchool = modelMappers.mapToSchool(schoolRequestDto);
            var savedSchool = schoolRepository.saveAndFlush(newSchool);
        return modelMappers.mapToSchoolResponseWithOutJoins(savedSchool);
    }

    //Update
    public SchoolResponseDto updateSchool(SchoolRequestDto schoolRequestDto, Integer id){
        var updateSchool = modelMappers.mapToSchool(schoolRequestDto);
            schoolRepository.findById(id).orElseThrow();
            updateSchool.setSchool_id(id);
            var updatedSchool = schoolRepository.save(updateSchool);
        return modelMappers.mapToSchoolResponseWithOutJoins(updatedSchool);
    }
    public SchoolResponseDto updateSchool(SchoolRequestDto schoolRequestDto){
        var updateSchool = new School();
            updateSchool.setSchoolName(schoolRequestDto.schoolName());
            //updateSchool.setSchoolCode(schoolRequestDto.schoolCode());
            var school = schoolRepository.findBySchoolCode(schoolRequestDto.schoolCode())
                    .orElseThrow();
                var schoolId = school.getSchool_id();
                updateSchool.setSchool_id(schoolId);
                var updatedSchool = schoolRepository.save(updateSchool);
        return modelMappers.mapToSchoolResponseWithOutJoins(updatedSchool);
    }


    //Retrieve
    public List<SchoolResponseDto> findAllSchools(){
        return schoolRepository.findAllQuery()
                .stream()
                .parallel()
                .map(modelMappers::mapToSchoolResponseWithOutJoins)
                .toList();
    }
    public List<SchoolResponseDto> findAllSchoolsUsingPages(Integer pageNumber, Integer pageSize, String sort){
        Pageable pageable;
        if(!sort.isEmpty())
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort).ascending());
        else
            pageable = PageRequest.of(pageNumber, pageSize);
        return schoolRepository.findAll(pageable)
                .getContent()
                .stream()
                .parallel()
                .map(modelMappers::mapToSchoolResponse)
                .toList();
    }

    public List<SchoolResponseDto> findAllSchoolsWithName(String name){
        return schoolRepository.findAllBySchoolNameLike(name)
                .stream()
                .parallel()
                .map(modelMappers::mapToSchoolResponseWithOutJoins)
                .toList();
    }
    public SchoolResponseDto findSchoolWithCode(String code){
        var school = schoolRepository.findBySchoolCode(code).orElse(new School());
        return modelMappers.mapToSchoolResponseWithOutJoins(school);
    }
    public SchoolResponseDto findSchoolWithId(Integer id){
        var school = schoolRepository.findById(id).orElse(new School());
        return modelMappers.mapToSchoolResponseWithOutJoins(school);
    }
    public School findBySchoolId(Integer id){
        return schoolRepository.findById(id).orElseThrow();
    }

    //Delete
    public Integer deleteSchoolWithCode(String code){
        return schoolRepository.deleteBySchoolCode(code);
    }
    public void deleteSchoolWithId(Integer id){
        schoolRepository.deleteById(id);
    }
}
