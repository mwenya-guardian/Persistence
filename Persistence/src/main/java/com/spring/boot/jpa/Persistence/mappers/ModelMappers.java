package com.spring.boot.jpa.Persistence.mappers;

import com.spring.boot.jpa.Persistence.dtos.course.CourseRequestDto;
import com.spring.boot.jpa.Persistence.dtos.course.CourseResponseDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import com.spring.boot.jpa.Persistence.dtos.lecture.LectureRequestDto;
import com.spring.boot.jpa.Persistence.dtos.lecture.LectureResponseDto;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerResponseDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramResponseDto;
import com.spring.boot.jpa.Persistence.dtos.school.SchoolRequestDto;
import com.spring.boot.jpa.Persistence.dtos.school.SchoolResponseDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentRequestDto;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.models.student.Student;
import org.springframework.stereotype.Component;

@Component
public class ModelMappers {
    public Department mapToDepartment(DepartmentRequestDto departmentRequestDto){
        Department newDepartment = new Department();
        return newDepartment;
    }
    public DepartmentResponseDto mapToDepartmentResponse(Department department){
        DepartmentResponseDto newDepartmentResponseDto = new DepartmentResponseDto();
        return newDepartmentResponseDto;
    }
    public Course mapToCourse(CourseRequestDto courseRequestDto){
        Course newCourse = new Course();
        return newCourse;
    }
    public CourseResponseDto mapToCourseResponse(Course course){
        CourseResponseDto newCourseResponseDto = new CourseResponseDto();
        return newCourseResponseDto;
    }
    public Lecture mapToLecture(LectureRequestDto lectureRequestDto){
        Lecture newLecture = new Lecture();
        return newLecture;
    }
    public LectureResponseDto mapToLectureResponse(Lecture lecture){
        LectureResponseDto newLectureResponseDto = new LectureResponseDto();
        return newLectureResponseDto;
    }
    public Lecturer mapToLecturer(LectureRequestDto lectureRequestDto){
        Lecturer newLecturer = new Lecturer();
        return newLecturer;
    }
    public LecturerResponseDto mapToLecturerResponse(Lecturer lecturer){
        LecturerResponseDto newLecturerResponseDto = new LecturerResponseDto();
        return newLecturerResponseDto;
    }
    public Program mapToProgram(ProgramRequestDto programRequestDto){
        Program newProgram = new Program();
        return newProgram;
    }
    public ProgramResponseDto mapToProgramResponse(Program program){
        ProgramResponseDto newProgramResponseDto = new ProgramResponseDto();
        return newProgramResponseDto;
    }
    public School mapToSchool(SchoolRequestDto schoolRequestDto){
        School school = new School();
        return school;
    }
    public SchoolResponseDto mapToSchoolResponse(){
        SchoolResponseDto schoolResponseDto = new SchoolResponseDto();
        return schoolResponseDto;
    }
    public Student mapToStudent(StudentRequestDto studentRequestDto){
        Student newStudent = new Student();
        return newStudent;
    }
}
