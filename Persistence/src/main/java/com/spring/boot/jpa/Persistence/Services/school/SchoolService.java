package com.spring.boot.jpa.Persistence.Services.school;

import com.spring.boot.jpa.Persistence.dtos.school.SchoolRequestDto;
import com.spring.boot.jpa.Persistence.dtos.school.SchoolResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.repositories.school.SchoolRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class SchoolService {
    private SchoolRepository schoolRepository;
    private ModelMappers modelMappers;

    //Create
    public SchoolResponseDto createSchool(SchoolRequestDto schoolRequestDto){
        var newSchool = modelMappers.mapToSchool(schoolRequestDto);
            var savedSchool = schoolRepository.saveAndFlush(newSchool);
        return modelMappers.mapToSchoolResponse(savedSchool);
    }

    //Update
    public SchoolResponseDto updateSchool(SchoolRequestDto schoolRequestDto, Integer id){
        var updateSchool = new School();
            updateSchool.setSchoolName(schoolRequestDto.schoolName());
            //updateSchool.setSchoolCode(schoolRequestDto.schoolCode());
            updateSchool.setSchool_id(id);
            var updatedSchool = schoolRepository.save(updateSchool);
        return modelMappers.mapToSchoolResponse(updatedSchool);
    }
    public SchoolResponseDto updateSchool(SchoolRequestDto schoolRequestDto) throws Exception {
        var updateSchool = new School();
            updateSchool.setSchoolName(schoolRequestDto.schoolName());
            //updateSchool.setSchoolCode(schoolRequestDto.schoolCode());
            var school = schoolRepository.findBySchoolCode(schoolRequestDto.schoolCode());
                if(school.isEmpty())
                    throw new Exception("School code is invalid");
                var schoolId = school.orElse(new School()).getSchool_id();
                updateSchool.setSchool_id(schoolId);
                var updatedSchool = schoolRepository.save(updateSchool);
        return modelMappers.mapToSchoolResponse(updatedSchool);
    }


    //Retrieve
    public List<SchoolResponseDto> findAllSchools(){
        return schoolRepository.findAll().stream()
                .parallel()
                .map(modelMappers::mapToSchoolResponse)
                .toList();
    }
    public List<SchoolResponseDto> findAllSchoolsWithName(String name){
        return schoolRepository.findAllBySchoolNameLike(name)
                .stream()
                .parallel()
                .map(modelMappers::mapToSchoolResponse)
                .toList();
    }
    public SchoolResponseDto findSchoolWithCode(String code){
        var school = schoolRepository.findBySchoolCode(code).orElse(new School());
        return modelMappers.mapToSchoolResponse(school);
    }
    public SchoolResponseDto findSchoolWithId(Integer id){
        var school = schoolRepository.findById(id).orElse(new School());
        return modelMappers.mapToSchoolResponse(school);
    }

    //Delete
    public void deleteSchoolWithCode(SchoolRequestDto schoolRequestDto){
        schoolRepository.deleteBySchoolCode(schoolRequestDto.schoolCode());
    }
    public void deleteSchoolWithId(Integer id){
        schoolRepository.deleteById(id);
    }
}
