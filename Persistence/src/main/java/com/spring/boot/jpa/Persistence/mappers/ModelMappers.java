package com.spring.boot.jpa.Persistence.mappers;

import com.spring.boot.jpa.Persistence.dtos.course.CourseRequestDto;
import com.spring.boot.jpa.Persistence.dtos.course.CourseResponseDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.department.DepartmentResponseDto;
import com.spring.boot.jpa.Persistence.dtos.lecture.LectureRequestDto;
import com.spring.boot.jpa.Persistence.dtos.lecture.LectureResponseDto;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerRequestDto;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerResponseDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramRequestDto;
import com.spring.boot.jpa.Persistence.dtos.program.ProgramResponseDto;
import com.spring.boot.jpa.Persistence.dtos.school.SchoolRequestDto;
import com.spring.boot.jpa.Persistence.dtos.school.SchoolResponseDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentResponseDto;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.models.student.Student;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;

@Component
public class ModelMappers {
    public Department mapToDepartment(@NotNull DepartmentRequestDto departmentRequestDto){
        Department newDepartment = new Department();
            newDepartment.setDepartmentCode(departmentRequestDto.departmentCode());
            newDepartment.setDepartmentName(departmentRequestDto.departmentName());
            newDepartment.setHOD(new Lecturer(
                    Integer.getInteger(
                            departmentRequestDto.lecturerId()
                    )));
            newDepartment.setSchool(new School(
                    Integer.getInteger(
                            departmentRequestDto.schoolId()
                    )));
        return newDepartment;
    }
    public DepartmentResponseDto mapToDepartmentResponse(@NotNull Department department){
        DepartmentResponseDto newDepartmentResponseDto = new DepartmentResponseDto(
                department.getDepartmentName(),
                department.getDepartmentCode(),
                department.getHOD().getFirstname().toUpperCase().charAt(0)
                        +" "+ department.getHOD().getLastname().toUpperCase(),
                department.getSchool().getSchoolName(),
                department.getSchool().getSchoolCode()
        );
        return newDepartmentResponseDto;
    }
    public Course mapToCourse(@NotNull CourseRequestDto courseRequestDto){
        Course newCourse = new Course();
            newCourse.setCourseName(courseRequestDto.courseName());
            newCourse.setCourseCode(courseRequestDto.courseName());
            newCourse.setDepartment(new Department(
                    Integer.getInteger(
                    courseRequestDto.departmentId())
            ));
        return newCourse;
    }
    public CourseResponseDto mapToCourseResponse(@NotNull Course course){
        CourseResponseDto newCourseResponseDto = new CourseResponseDto(
                course.getCourseCode(),
                course.getCourseName(),
                course.getDepartment().getDepartmentName(),
                course.getDepartment().getDepartmentCode());
        return newCourseResponseDto;
    }
    public Lecture mapToLecture(@NotNull LectureRequestDto lectureRequestDto){
        Lecture newLecture = new Lecture();
            newLecture.setLecturer(new Lecturer(
                            Integer.getInteger(lectureRequestDto.lecturerId()))
            );
            newLecture.setCourse(new Course(
                    Integer.getInteger(
                            lectureRequestDto.courseId())
            ));
            newLecture.setCode(lectureRequestDto.code());
            newLecture.setStartTime(lectureRequestDto.startTime());
            newLecture.setEndTime(lectureRequestDto.endTime());
        return newLecture;
    }
    public LectureResponseDto mapToLectureResponse(@NotNull Lecture lecture){
        LectureResponseDto newLectureResponseDto = new LectureResponseDto(
                lecture.getCode(),
                lecture.getCourse().getCourseName(),
                lecture.getCourse().getCourseCode(),
                lecture.getLecturer().getLastname().toUpperCase(),
                lecture.getStartTime(),
                lecture.getEndTime()
        );
        return newLectureResponseDto;
    }
    public Lecturer mapToLecturer(@NotNull LecturerRequestDto lecturerRequestDto){
        Lecturer newLecturer = new Lecturer();
            newLecturer.setLectureId(lecturerRequestDto.lecturerId());
            newLecturer.setDepartment(new Department(
                    Integer.getInteger(lecturerRequestDto.departmentId())
            ));
            newLecturer.setFirstname(lecturerRequestDto.firstname());
            newLecturer.setLastname(lecturerRequestDto.lastname());
            newLecturer.setAddress(lecturerRequestDto.address());
            newLecturer.setDistrict(lecturerRequestDto.district());
            newLecturer.setProvince(lecturerRequestDto.province());
            newLecturer.setPhoneNumber(lecturerRequestDto.phoneNumber());
            newLecturer.setNationality(lecturerRequestDto.nationality());
            newLecturer.setDob(new Date(
                    lecturerRequestDto.dob().getTime()
            ));
            newLecturer.setEmail(lecturerRequestDto.email());
            newLecturer.setNrcNumber(lecturerRequestDto.nrcNumber());
        return newLecturer;
    }
    public LecturerResponseDto mapToLecturerResponse(@NotNull Lecturer lecturer){
        LecturerResponseDto newLecturerResponseDto = new LecturerResponseDto(
                lecturer.getNrcNumber(),
                lecturer.getLectureId(),
                lecturer.getFirstname(),
                lecturer.getLastname(),
                new Date(lecturer.getDob().getTime()),
                lecturer.getAddress(),
                lecturer.getProvince(),
                lecturer.getDistrict(),
                lecturer.getNationality(),
                lecturer.getPhoneNumber(),
                lecturer.getEmail(),
                lecturer.getDepartment().getDepartmentCode(),
                lecturer.getDepartment().getDepartmentName()
        );
        return newLecturerResponseDto;
    }
    public Program mapToProgram(@NotNull ProgramRequestDto programRequestDto){
        Program newProgram = new Program();
            newProgram.setProgramCode(programRequestDto.programCode());
            newProgram.setProgramName(programRequestDto.programName());
            newProgram.setDepartment(new Department(
                    Integer.getInteger(programRequestDto.departmentId())
            ));
        newProgram.setSchool(new School(
                Integer.getInteger(programRequestDto.schoolId())
        ));
        return newProgram;
    }
    public ProgramResponseDto mapToProgramResponse(@NotNull Program program){
        ProgramResponseDto newProgramResponseDto = new ProgramResponseDto(
                program.getProgramCode(),
                program.getProgramName(),
                program.getSchool().getSchoolCode(),
                program.getSchool().getSchoolName(),
                program.getDepartment().getDepartmentName(),
                program.getDepartment().getDepartmentName(),
                program.getProgramCourseList().stream()
                        .parallel()
                        .map(this::mapToCourseResponse)
                        .toList()
        );
        return newProgramResponseDto;
    }
    public School mapToSchool(@NotNull SchoolRequestDto schoolRequestDto){
        School school = new School();
            school.setSchoolCode(schoolRequestDto.schoolCode());
            school.setSchoolName(schoolRequestDto.schoolName());
        return school;
    }
    public SchoolResponseDto mapToSchoolResponse(@NotNull School school){
        SchoolResponseDto schoolResponseDto = new SchoolResponseDto(
                school.getSchoolCode(),
                school.getSchoolName()
        );
        return schoolResponseDto;
    }
    public Student mapToStudent(@NotNull StudentRequestDto studentRequestDto){
        Student newStudent = new Student();
            newStudent.setStudentId(studentRequestDto.studentId());
            newStudent.setFirstname(studentRequestDto.firstname());
            newStudent.setLastname(studentRequestDto.lastname());
            newStudent.setDistrict(studentRequestDto.district());
            newStudent.setProvince(studentRequestDto.province());
            newStudent.setNationality(studentRequestDto.nationality());
            newStudent.setDob(new Date(studentRequestDto.dob().getTime()));
            newStudent.setNrcNumber(studentRequestDto.nrcNumber());
            newStudent.setPhoneNumber(studentRequestDto.phoneNumber());
            newStudent.setEmail(studentRequestDto.email());
            newStudent.setAddress(studentRequestDto.address());
            newStudent.setEnrollmentDate(new Date(studentRequestDto.enrollmentDate().getTime()));
            newStudent.setSchool(new School(
                    Integer.getInteger(studentRequestDto.schoolId())
            ));
            newStudent.setDepartment(new Department(
                    Integer.getInteger(studentRequestDto.schoolId())
            ));
            newStudent.setSchool(new School(
                    Integer.getInteger(studentRequestDto.departmentId())
            ));
        return newStudent;
    }
    public StudentResponseDto mapToStudentResponse(@NotNull Student student){
        StudentResponseDto studentResponseDto = new StudentResponseDto(
                student.getNrcNumber(),
                student.getStudentId(),
                student.getFirstname(),
                student.getLastname(),
                student.getAddress(),
                student.getProvince(),
                student.getDistrict(),
                student.getNationality(),
                student.getPhoneNumber(),
                student.getEmail(),
                new java.util.Date(student.getDob().getTime()),
                new java.util.Date(student.getEnrollmentDate().getTime())
//                student.getSchool().getSchoolName(),
//                student.getProgram().getProgramName(),
//                student.getDepartment().getDepartmentName(),
//                student.getSchool().getSchoolCode(),
//                student.getProgram().getProgramCode(),
//                student.getDepartment().getDepartmentCode()

        );
        return studentResponseDto;
    }
}
