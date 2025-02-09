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
import com.spring.boot.jpa.Persistence.models.UserBaseClass;
import com.spring.boot.jpa.Persistence.models.course.Course;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecture.Lecture;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.program.ProgramCourseList;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.models.student.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ModelMappers {
    public Department mapToDepartment(@NotNull DepartmentRequestDto departmentRequestDto){
        Department newDepartment = new Department();
            newDepartment.setDepartmentCode(departmentRequestDto.departmentCode());
            newDepartment.setDepartmentName(departmentRequestDto.departmentName());
        return newDepartment;
    }
    public DepartmentResponseDto mapToDepartmentResponse(@NotNull Department department){
        DepartmentResponseDto newDepartmentResponseDto = new DepartmentResponseDto(
                department.getDepartmentName(),
                department.getDepartmentCode(),
                department.getHod() != null? department.getHod().getFirstname().toUpperCase().charAt(0)
                        +" "+ department.getHod().getLastname().toUpperCase(): null,
                department.getSchool().getSchoolName(),
                department.getSchool().getSchoolCode()
        );
        return newDepartmentResponseDto;
    }
    public Course mapToCourse(@NotNull CourseRequestDto courseRequestDto){
        Course newCourse = new Course();
            newCourse.setCourseName(courseRequestDto.courseName());
            newCourse.setCourseCode(courseRequestDto.courseCode());
        return newCourse;
    }
    public CourseResponseDto mapToCourseResponse(@NotNull Course course){
        CourseResponseDto newCourseResponseDto = new CourseResponseDto(
                course.getCourseId(),
                course.getCourseCode(),
                course.getCourseName(),
                course.getDepartment().getDepartmentName(),
                course.getDepartment().getDepartmentCode());
        return newCourseResponseDto;
    }
    public Lecture mapToLecture(@NotNull LectureRequestDto lectureRequestDto){
        Lecture newLecture = new Lecture();
            newLecture.setLectureCode(lectureRequestDto.code());
            newLecture.setStartTime(lectureRequestDto.startTime());
            newLecture.setEndTime(lectureRequestDto.endTime());
        return newLecture;
    }
    public LectureResponseDto mapToLectureResponse(@NotNull Lecture lecture){
        LectureResponseDto newLectureResponseDto = new LectureResponseDto(
                lecture.getLectureCode(),
                lecture.getCourse().getCourseName(),
                lecture.getCourse().getCourseCode(),
                lecture.getLecturer().getFirstname().toUpperCase().charAt(0) + ". "
                            + lecture.getLecturer().getLastname().toUpperCase(),
                lecture.getStartTime(),
                lecture.getEndTime()
        );
        return newLectureResponseDto;
    }
    public Lecturer mapToLecturer(@NotNull LecturerRequestDto lecturerRequestDto){
        Lecturer newLecturer = new Lecturer();
            newLecturer.setDepartment(new Department(
                  lecturerRequestDto.departmentId()
            ));
            newLecturer.setFirstname(lecturerRequestDto.firstname());
            newLecturer.setLastname(lecturerRequestDto.lastname());
            newLecturer.setAddress(lecturerRequestDto.address());
            newLecturer.setDistrict(lecturerRequestDto.district());
            newLecturer.setProvince(lecturerRequestDto.province());
            newLecturer.setPhoneNumber(lecturerRequestDto.phoneNumber());
            newLecturer.setNationality(lecturerRequestDto.nationality());
            newLecturer.setDob(lecturerRequestDto.dob());
            newLecturer.setEmail(lecturerRequestDto.email());
            newLecturer.setNrcNumber(lecturerRequestDto.nrcNumber());
        return newLecturer;
    }
    public LecturerResponseDto mapToLecturerResponse(@NotNull Lecturer lecturer){
        LecturerResponseDto newLecturerResponseDto = new LecturerResponseDto(
                lecturer.getNrcNumber(),
                lecturer.getLecturerNumber(),
                lecturer.getFirstname(),
                lecturer.getLastname(),
                lecturer.getDob(),
                lecturer.getEmploymentDate(),
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
        return newProgram;
    }
    public Program mapToNewProgram(@NotNull ProgramRequestDto programRequestDto){
        Program newProgram = new Program();
        newProgram.setProgramCode(programRequestDto.programCode());
        newProgram.setProgramName(programRequestDto.programName());
        return newProgram;
    }
    public ProgramResponseDto mapToProgramResponse(@NotNull Program program){
        ProgramResponseDto newProgramResponseDto = new ProgramResponseDto(
                program.getProgramId(),
                program.getProgramCode(),
                program.getProgramName(),
                program.getSchool().getSchoolCode(),
                program.getSchool().getSchoolName(),
                program.getDepartment().getDepartmentName(),
                program.getDepartment().getDepartmentName(),
                program.getProgramCourseList()
                        .orElse(new ArrayList<ProgramCourseList>())
                        .stream()
                        .parallel()
                        .map(ProgramCourseList::getCourse)
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
    public SchoolResponseDto mapToSchoolResponseWithOutJoins(@NotNull School school){
        SchoolResponseDto schoolResponseDto = SchoolResponseDto.dtoWithNameAndCodeOnly(
                school.getSchoolCode(),
                school.getSchoolName()
        );
        return schoolResponseDto;
    }
    public SchoolResponseDto mapToSchoolResponse(@NotNull School school){
        SchoolResponseDto schoolResponseDto = new SchoolResponseDto(
                school.getSchoolCode(),
                school.getSchoolName(),
                school.getDepartments().stream().map(this::mapToDepartmentResponse).toList(),
                school.getPrograms().stream().map(this::mapToProgramResponse).toList()
        );
        return schoolResponseDto;
    }
    public Student mapToStudent(@NotNull StudentRequestDto studentRequestDto){
        Student newStudent = new Student();
            newStudent.setFirstname(studentRequestDto.firstname());
            newStudent.setLastname(studentRequestDto.lastname());
            newStudent.setDistrict(studentRequestDto.district());
            newStudent.setProvince(studentRequestDto.province());
            newStudent.setNationality(studentRequestDto.nationality());
            newStudent.setDob(studentRequestDto.dob());
            newStudent.setNrcNumber(studentRequestDto.nrcNumber());
            newStudent.setPhoneNumber(studentRequestDto.phoneNumber());
            newStudent.setEmail(studentRequestDto.email());
            newStudent.setAddress(studentRequestDto.address());
//            newStudent.setEnrollmentDate(new Date(studentRequestDto.enrollmentDate().getTime()));
//            newStudent.setSchool(new School(studentRequestDto.schoolCode()));
//            newStudent.setDepartment(new Department(studentRequestDto.departmentId()));
//            newStudent.setProgram(new Program(studentRequestDto.programId()));
        return newStudent;
    }

    public Student mapToNewStudent(@NotNull StudentRequestDto studentRequestDto){
        Student newStudent = new Student();
        newStudent.setFirstname(studentRequestDto.firstname());
        newStudent.setLastname(studentRequestDto.lastname());
        newStudent.setDistrict(studentRequestDto.district());
        newStudent.setProvince(studentRequestDto.province());
        newStudent.setNationality(studentRequestDto.nationality());
        newStudent.setDob(studentRequestDto.dob());
        newStudent.setNrcNumber(studentRequestDto.nrcNumber());
        newStudent.setPhoneNumber(studentRequestDto.phoneNumber());
        newStudent.setEmail(studentRequestDto.email());
        newStudent.setAddress(studentRequestDto.address());
        return newStudent;
    }
    public StudentResponseDto mapToStudentResponse(@NotNull Student student){
        StudentResponseDto studentResponseDto = new StudentResponseDto(
                student.getNrcNumber(),
                student.getStudentNumber(),
                student.getFirstname(),
                student.getLastname(),
                student.getAddress(),
                student.getProvince(),
                student.getDistrict(),
                student.getNationality(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getDob(),
                student.getEnrollmentDate(),
                student.getSchool().getSchoolName(),
                student.getProgram().getProgramName(),
                student.getDepartment().getDepartmentName(),
                student.getSchool().getSchoolCode(),
                student.getProgram().getProgramCode(),
                student.getDepartment().getDepartmentCode()
        );
        return studentResponseDto;
    }
    public  StudentResponseDto mapToStudentResponseWithOutJoin(@NotNull Student student){
        return StudentResponseDto.withOutJoins(
                student.getNrcNumber(),
                student.getStudentNumber(),
                student.getFirstname(),
                student.getLastname(),
                student.getAddress(),
                student.getProvince(),
                student.getDistrict(),
                student.getNationality(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getDob(),
                student.getEnrollmentDate()
        );
    }
}
