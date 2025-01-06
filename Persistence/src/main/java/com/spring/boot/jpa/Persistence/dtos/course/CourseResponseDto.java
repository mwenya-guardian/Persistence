package com.spring.boot.jpa.Persistence.dtos.course;

public record CourseResponseDto (
    String courseCode,
    String courseName,
    String departmentName,
    String departmentCode
) {
    public CourseResponseDto( String courseCode,
                              String courseName){
        this(courseCode, courseName, "", "");
    }
}
