package com.spring.boot.jpa.Persistence.Services.course;

import com.github.javafaker.Bool;
import com.spring.boot.jpa.Persistence.Services.department.DepartmentService;
import com.spring.boot.jpa.Persistence.dtos.course.CourseRequestDto;
import com.spring.boot.jpa.Persistence.dtos.course.CourseResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.repositories.course.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;
    private DepartmentService departmentService;
    private ModelMappers modelMappers;

    //Create
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto){
        var newCourse = modelMappers.mapToCourse(courseRequestDto);
            var department = departmentService.findByDepartmentCode(courseRequestDto.departmentCode());
            newCourse.setDepartment(department);
        var savedCourse = courseRepository.saveAndFlush(newCourse);
        return modelMappers.mapToCourseResponse(savedCourse);
    }

    //Update
    public CourseResponseDto updateCourse(CourseRequestDto courseRequestDto, Integer Id){
        var newCourse = modelMappers.mapToCourse(courseRequestDto);
            newCourse.setCourseId(Id);
                var savedCourse = courseRepository.save(newCourse);
        return modelMappers.mapToCourseResponse(savedCourse);
    }
    
    public Map<CourseResponseDto, Boolean> updateCourse(CourseRequestDto courseRequestDto, String courseCode){
        var newCourse = modelMappers.mapToCourse(courseRequestDto);
        Integer Id = courseRepository
                    .findByCourseCode(courseCode)
                    .orElseThrow()
                    .getCourseId();
        newCourse.setCourseId(Id);
        var savedCourse = courseRepository.save(newCourse);
        var map = new Hashtable<CourseResponseDto, Boolean>();
                map.put(modelMappers.mapToCourseResponse(savedCourse),
                savedCourse.isEqual(newCourse)
                );
        return map;
    }

    //Delete
    public void deleteCourse(Integer courseId){
        courseRepository.deleteById(courseId);
    }
    
    public Integer deleteCourse(String courseCode){
        return courseRepository.deleteByCourseCode(courseCode);
    }
    
    public void deleteCourse(CourseRequestDto courseRequestDto){
        var course = modelMappers.mapToCourse(courseRequestDto);
        courseRepository.delete(course);
    }

    //Retrieve
    public List<CourseResponseDto> findAllCoursesByDepartment(String departmentCode){
        var department = departmentService.getDepartmentId(departmentCode);
        return courseRepository.findAllByDepartment(department)
                .stream()
                .map(modelMappers::mapToCourseResponse)
                .toList();
    }
    
    public List<CourseResponseDto> findAllCourses(){
        List<Course> courseList = courseRepository.findAll();
        List<CourseResponseDto> courseResponseDtoList = courseList
                .stream()
                .map(modelMappers::mapToCourseResponse)
                .toList();
        return courseResponseDtoList;
    }
    
    public List<CourseResponseDto> findAllCourseByName(String courseName){
        List<Course> courses = courseRepository.findAllByCourseNameContaining(courseName);
        List<CourseResponseDto> responseDtos = courses
                .stream()
                .map(modelMappers::mapToCourseResponse)
                .toList();
        return responseDtos;
    }
    public CourseResponseDto findCourseWithCourseCode(String code){
        return courseRepository.findByCourseCode(code)
                .map(modelMappers::mapToCourseResponse)
                .orElseThrow();
    }
    public Integer getCourseId(String code){
        return courseRepository.findByCourseCode(code)
                .orElseThrow()
                .getCourseId();
    }

    public Course findByCourseCode(String courseCode){
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow();
    }


//    public List<Course> findAllCoursesBySchool(){
//        return courseRepository.findAll();
//    }
//    public List<Course> findAllCoursesByProgram(){
//        return courseRepository.findAll();
//    }
}
