package com.spring.boot.jpa.Persistence.Services.course;

import com.spring.boot.jpa.Persistence.repositories.course.CourseRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;
}
