package com.spring.boot.jpa.Persistence.Services.program;

import com.spring.boot.jpa.Persistence.repositories.program.ProgramCourseListRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
public class ProgramCourseListService {
    private ProgramCourseListRepository programCourseListRepository;
}
