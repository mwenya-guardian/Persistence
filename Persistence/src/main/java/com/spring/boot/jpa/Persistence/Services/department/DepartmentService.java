package com.spring.boot.jpa.Persistence.Services.department;

import com.spring.boot.jpa.Persistence.dtos.department.DepartmentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.repositories.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMappers modelMappers;

    //Create And Update
    @ResponseStatus(value = HttpStatus.CREATED)
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto){
        var department = modelMappers.mapToDepartment(departmentRequestDto);
        var savedDepartment = departmentRepository.save(department);
        return modelMappers.mapToDepartmentResponse(savedDepartment);
    }

    //Update
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public DepartmentResponseDto updateDepartment(DepartmentRequestDto departmentRequestDto, Integer Id){
        var newDepartment = modelMappers.mapToDepartment(departmentRequestDto);
            newDepartment.setId(Id);
                var savedDepartment = departmentRepository.save(newDepartment);
        return modelMappers.mapToDepartmentResponse(savedDepartment);
    }
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public DepartmentResponseDto updateDepartment(DepartmentRequestDto departmentRequestDto, String departmentCode){
        var newDepartment = modelMappers.mapToDepartment(departmentRequestDto);
            Integer Id = departmentRepository
                        .findByDepartmentCode(departmentCode)
                        .getId();
            newDepartment.setId(Id);
                var savedDepartment = departmentRepository.save(newDepartment);
        return modelMappers.mapToDepartmentResponse(savedDepartment);
    }

    //Retrieve
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<DepartmentResponseDto> findAllDepartment(){
        var departmentList = departmentRepository.findAll();
        return departmentList
                .stream()
                .parallel()
                .map(modelMappers::mapToDepartmentResponse)
                .toList();
    }
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<DepartmentResponseDto> findAllDepartmentsByName(String name){
        var departmentList = departmentRepository.findAllByDepartmentNameContaining(name);
        return departmentList
                .stream()
                .parallel()
                .map(modelMappers::mapToDepartmentResponse)
                .toList();
    }
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public DepartmentResponseDto findByDepartmentCode(String code){
        var department = departmentRepository.findByDepartmentCode(code);
        return modelMappers.mapToDepartmentResponse(department);
    }
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public DepartmentResponseDto findByDepartmentId(Integer Id){
        var department = departmentRepository.findById(Id).orElse(null);
        return modelMappers.mapToDepartmentResponse(department);
    }

    //Delete
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDepartment(DepartmentRequestDto departmentRequestDto){
        var department = modelMappers.mapToDepartment(departmentRequestDto);
        departmentRepository.delete(department);
    }
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDepartment(Integer Id){
        departmentRepository.deleteById(Id);
    }
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDepartment(String code){
        departmentRepository.deleteByDepartmentCode(code);
    }

}
