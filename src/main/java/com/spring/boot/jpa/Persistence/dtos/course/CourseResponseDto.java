package com.spring.boot.jpa.Persistence.dtos.course;

public record CourseResponseDto (
    Integer courseId,
    String courseCode,
    String courseName,
    String departmentName,
    String departmentCode
) {
    public CourseResponseDto( Integer id,
                              String courseCode,
                              String courseName){
        this(id, courseCode, courseName, "", "");
    }
}
