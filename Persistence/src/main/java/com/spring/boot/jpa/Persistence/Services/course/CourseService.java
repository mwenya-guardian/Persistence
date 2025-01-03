package com.spring.boot.jpa.Persistence.Services.course;

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

import java.util.List;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;
    private ModelMappers modelMappers;

    //Create
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto){
        var newCourse = modelMappers.mapToCourse(courseRequestDto);
        var savedCourse = courseRepository.save(newCourse);
        return modelMappers.mapToCourseResponse(savedCourse);
    }

    //Update
    public CourseResponseDto updateCourse(CourseRequestDto courseRequestDto, Integer Id){
        var newCourse = modelMappers.mapToCourse(courseRequestDto);
            newCourse.setCourse_id(Id);
                var savedCourse = courseRepository.save(newCourse);
        return modelMappers.mapToCourseResponse(savedCourse);
    }
    
    public CourseResponseDto updateCourse(CourseRequestDto courseRequestDto, String courseCode){
        var newCourse = modelMappers.mapToCourse(courseRequestDto);
        Integer Id = courseRepository
                    .findByCourseCode(courseCode)
                    .getCourse_id();
        newCourse.setCourse_id(Id);
        var savedCourse = courseRepository.save(newCourse);
        return modelMappers.mapToCourseResponse(savedCourse);
    }

    //Delete
    public void deleteCourse(Integer courseId){
        courseRepository.deleteById(courseId);
    }
    
    public void deleteCourse(String courseCode){
        courseRepository.deleteByCourseCode(courseCode);
    }
    
    public void deleteCourse(CourseRequestDto courseRequestDto){
        var course = modelMappers.mapToCourse(courseRequestDto);
        courseRepository.delete(course);
    }

    //Retrieve
    public List<CourseResponseDto> findAllCoursesByDepartment(Integer id){
        var department = new Department();
            department.setId(id);
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



//    public List<Course> findAllCoursesBySchool(){
//        return courseRepository.findAll();
//    }
//    public List<Course> findAllCoursesByProgram(){
//        return courseRepository.findAll();
//    }
}
