package com.spring.boot.jpa.Persistence.services.department;

import com.spring.boot.jpa.Persistence.services.lecturer.LecturerService;
import com.spring.boot.jpa.Persistence.services.school.SchoolService;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.repositories.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMappers modelMappers;
    //Inter-Service
    private LecturerService lecturerService;
    private SchoolService schoolService;

    //Create
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto){
        var department = modelMappers.mapToDepartment(departmentRequestDto);
            if(departmentRequestDto.lecturerNumber() != null){
                Long.valueOf(departmentRequestDto.lecturerNumber());
                var hod = lecturerService.findByLecturerNumber(departmentRequestDto.lecturerNumber());
                department.setHod(hod);
            }
            var school = schoolService.findBySchoolCode(departmentRequestDto.schoolCode());
            department.setSchool(school);
        var savedDepartment = departmentRepository.saveAndFlush(department);
        return modelMappers.mapToDepartmentResponse(savedDepartment);
    }

    //Update
    public DepartmentResponseDto updateDepartment(DepartmentRequestDto departmentRequestDto, Integer Id){
        var newDepartment = modelMappers.mapToDepartment(departmentRequestDto);
            newDepartment.setId(Id);
                var savedDepartment = departmentRepository.save(newDepartment);
        return modelMappers.mapToDepartmentResponse(savedDepartment);
    }
    
    public DepartmentResponseDto updateDepartment(DepartmentRequestDto departmentRequestDto, String departmentCode){
        var newDepartment = modelMappers.mapToDepartment(departmentRequestDto);
            Integer Id = departmentRepository
                        .findByDepartmentCodeQuery(departmentCode)
                        .orElseThrow()
                        .getId();
            newDepartment.setId(Id);
                var savedDepartment = departmentRepository.save(newDepartment);
        return modelMappers.mapToDepartmentResponse(savedDepartment);
    }
    public DepartmentResponseDto updateDepartmentHod(String code, String lecturerNumber){
        var department = departmentRepository.findByDepartmentCodeQuery(code).orElseThrow();
        var lecturer = lecturerService.findByLecturerNumber(lecturerNumber);
            department.setHod(lecturer);
            var updatedDepartment = departmentRepository.save(department);
        return modelMappers.mapToDepartmentResponse(updatedDepartment);
    }

    //Retrieve
    public List<DepartmentResponseDto> findAllDepartmentsUsingPages(Integer pageNumber, Integer pageSize, String sort){
        Pageable pageable;
        if(!sort.isEmpty())
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort).ascending());
        else
            pageable = PageRequest.of(pageNumber, pageSize);
        return departmentRepository.findAll(pageable)
                .stream()
                .map(modelMappers::mapToDepartmentResponse)
                .toList();
    }
    public List<DepartmentResponseDto> findAllDepartment(){
        var departmentList = departmentRepository.findAll();
        return departmentList
                .stream()
                .parallel()
                .map(modelMappers::mapToDepartmentResponse)
                .toList();
    }
    
    public List<DepartmentResponseDto> findAllDepartmentsByName(String name){
        var departmentList = departmentRepository.findAllByDepartmentNameContaining(name);
        return departmentList
                .stream()
                .parallel()
                .map(modelMappers::mapToDepartmentResponse)
                .toList();
    }
    
    public DepartmentResponseDto findWithDepartmentCode(String code){
        var department = departmentRepository.findByDepartmentCodeQuery(code).orElseThrow();
        return modelMappers.mapToDepartmentResponse(department);
    }
    
    public DepartmentResponseDto findByDepartmentWithId(Integer Id){
        var department = departmentRepository.findById(Id).orElseThrow();
        return modelMappers.mapToDepartmentResponse(department);
    }


    //Delete
    public void deleteDepartment(DepartmentRequestDto departmentRequestDto){
        var department = modelMappers.mapToDepartment(departmentRequestDto);
        departmentRepository.delete(department);
    }
    
    public void deleteDepartment(Integer Id){
        departmentRepository.deleteById(Id);
    }
    
    public Integer deleteDepartment(String code){
        return departmentRepository.deleteByDepartmentCodeQuery(code);
    }

    public Integer getDepartmentId(String code){
        return departmentRepository.findByDepartmentCodeQuery(code)
                .orElseThrow()
                .getId();
    }
    //Inter-Service Communication
    public Department findByDepartmentId(Integer Id){
        return departmentRepository.findById(Id).orElseThrow();
    }
    public Department findByDepartmentCode(String code){
        return departmentRepository.findByDepartmentCode(code).orElseThrow();
    }

}
