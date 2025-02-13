package com.spring.boot.jpa.Persistence.Services.lecturer;

import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerRequestDto;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.repositories.lecturer.LecturerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@Service
@AllArgsConstructor
public class LecturerService {
    private LecturerRepository lecturerRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private PasswordEncoder passwordEncoder;
    private ModelMappers modelMappers;

    //Create
    public LecturerResponseDto createLecturer(LecturerRequestDto lecturerRequestDto){
        var lecturer = modelMappers.mapToLecturer(lecturerRequestDto);
            var lecturerNumber = new Random();
            long number = lecturerNumber.nextLong(2018000000, 2040999999);
            lecturer.setLecturerNumber(String.valueOf(number));
            lecturer.setUsername(lecturer.getLecturerNumber());
            lecturer.setPassword(passwordEncoder.encode(lecturer.getLecturerNumber()));
            lecturer = lecturerRepository.saveAndFlush(lecturer);
        return modelMappers.mapToLecturerResponse(lecturer);
    }

    //Retrieve
    public List<LecturerResponseDto> findAllLecturer(){
        return lecturerRepository.findAll()
                .stream()
                .parallel()
                .map(modelMappers::mapToLecturerResponse)
                .toList();
    }
    public List<LecturerResponseDto> findAllLecturersUsingPaging(Integer pageNumber, Integer pageSize, String sort){
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sort));
        return lecturerRepository.findAll(page)
                .stream()
                .map(modelMappers::mapToLecturerResponse)
                .toList();
    }
    public LecturerResponseDto findLecturerWithNrcNumber(String nrc){
        return lecturerRepository.findByNrcNumber(nrc)
                .map(modelMappers::mapToLecturerResponse)
                .orElseThrow();
    }
    public LecturerResponseDto findLecturerWithLecturerNumber(String id){
        return lecturerRepository.findByLecturerNumber(id)
                .map(modelMappers::mapToLecturerResponse)
                .orElseThrow();
    }
    public List<LecturerResponseDto> findAllLecturersWithDepartment(String departmentId){
        return lecturerRepository.findAllByDepartmentId(departmentId)
                .stream()
                .parallel()
                .map(modelMappers::mapToLecturerResponse)
                .toList();
    }
    public List<LecturerResponseDto> findAllLecturersWithDepartmentEntity(String departmentId){
        return lecturerRepository.findAllByDepartment(new Department(
                Integer.getInteger(departmentId)
                ))
                .stream()
                .parallel()
                .map(modelMappers::mapToLecturerResponse)
                .toList();
    }
    //Custom
    public List<Object[]> findAllLecturersWithCustomFieldsSafe(String... args){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Lecturer> lecturerRoot = criteriaQuery.from(Lecturer.class);
        List<Selection<?>> columns = new ArrayList<>();
        Arrays.stream(args)
                .forEach(arg ->{
                    if(!arg.equals("password") && !arg.equals("username")) {
                        final String column = arg + "";
                        columns.add(lecturerRoot.get(column));
                    }
                });
        criteriaQuery.multiselect(columns);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    public List<Object[]> findLecturerWithCustomFieldsSafe(String id, String... args){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Lecturer> lecturerRoot = criteriaQuery.from(Lecturer.class);
        List<Selection<?>> columns = new ArrayList<>();
        Arrays.stream(args)
                .forEach(arg ->{
                    if(!arg.equals("password") && !arg.equals("username")) {
                        final String column = String.valueOf(arg);
                        columns.add(lecturerRoot.get(column));
                    }
                });
        Predicate studentNumberEqualTo = criteriaBuilder.equal(lecturerRoot.get("lecturerNumber"), id);
        criteriaQuery.multiselect(columns).where(studentNumberEqualTo);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    //Update
    public LecturerResponseDto updateLecturer(LecturerRequestDto lecturerRequestDto, String id){
        var lecturer = modelMappers.mapToLecturer(lecturerRequestDto);
        var retrieved = lecturerRepository.findByLecturerNumber(id).orElseThrow();
            lecturer.setId(retrieved.getId());
            lecturer = lecturerRepository.save(lecturer);
        return modelMappers.mapToLecturerResponse(lecturer);
    }
    //Delete
    public Integer deleteLecturerWithNrc(String nrc){
        return lecturerRepository.deleteByNrcNumber(nrc);
    }
    public Integer deleteLecturerWithLecturerId(String id){
        return lecturerRepository.deleteByLecturerNumber(id);
    }


    public Integer getLecturerId(String lectureNumber){
        return lecturerRepository.findByLecturerNumber(lectureNumber)
                .orElseThrow()
                .getId();
    }

    //Inter-Service Communication
    public Lecturer findByLecturerNumber(String number){
        return lecturerRepository.findByLecturerNumber(number)
                .orElseThrow();
    }
}
